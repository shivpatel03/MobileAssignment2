package com.example.mobileassignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    private EditText searchInput;
    private Button searchButton;

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
        searchInput = findViewById(R.id.searchInput);
        searchButton = findViewById(R.id.searchButton);

        allAddresses = findViewById(R.id.allAddresses);
        allAddresses.setLayoutManager(new LinearLayoutManager(this));

        locationList = dbHelper.getEntries();

        adapter = new LocationAdapter(locationList, this);
        allAddresses.setAdapter(adapter);
    }

    public void searchDatabase(View view) {
        String searchTerm = searchInput.getText().toString().trim();
        if (!searchTerm.isEmpty()) {
            locationList = dbHelper.search(searchTerm);
            adapter.updateData(locationList);
        } else {
            locationList = dbHelper.getEntries();
            adapter.updateData(locationList);
        }
    }

    // onclick for showing location
    @Override
    public void onLocationClick(Location location) {
        // Open a new activity and pass the latitude and longitude
        Intent intent = new Intent(MainActivity.this, LocationDetailActivity.class);
        intent.putExtra("addressId", location.getId());
        intent.putExtra("address", location.getAddress());
        intent.putExtra("latitude", location.getLatitude());
        intent.putExtra("longitude", location.getLongitude());
        startActivity(intent);
    }

    // redirect to class to add a new address
    public void addNewAddress(View view) {
        Intent intent = new Intent(MainActivity.this, NewAddress.class);
        startActivity(intent);
    }
}
