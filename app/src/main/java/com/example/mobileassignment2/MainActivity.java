package com.example.mobileassignment2;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LocationAdapter.OnLocationClickListener {

    private DatabaseHelper dbHelper;
    private RecyclerView allAddresses;
    private LocationAdapter adapter;
    private List<Location> locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.allAddresses), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);

        dbHelper.addEntry("Scarborough", 43.7764, 79.2318);

        allAddresses = findViewById(R.id.allAddresses);
        allAddresses.setLayoutManager(new LinearLayoutManager(this));

        locationList = dbHelper.getEntries();

        adapter = new LocationAdapter(locationList, this);
        allAddresses.setAdapter(adapter);
    }

    // Implement the onLocationClick method
    @Override
    public void onLocationClick(Location location) {
        // Open a new activity and pass the latitude and longitude
        Intent intent = new Intent(MainActivity.this, LocationDetailActivity.class);
        intent.putExtra("address", location.getAddress());
        intent.putExtra("latitude", location.getLatitude());
        intent.putExtra("longitude", location.getLongitude());
        startActivity(intent);
    }
}
