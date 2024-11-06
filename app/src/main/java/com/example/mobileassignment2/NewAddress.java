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

public class NewAddress extends AppCompatActivity {

    private EditText addressInput;
    private EditText longitudeInput;
    private EditText latitudeInput;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_address);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);

        addressInput = findViewById(R.id.addressInput);
        longitudeInput = findViewById(R.id.longitudeInput);
        latitudeInput = findViewById(R.id.latitudeInput);
    }

    public void submitNewAddress(View view) {
        String address = addressInput.getText().toString();
        String longitudeString = longitudeInput.getText().toString();
        String latitudeString = latitudeInput.getText().toString();

        double longitude = Double.parseDouble(longitudeString);
        double latitude = Double.parseDouble(latitudeString);

        dbHelper.addEntry(address, longitude, latitude);

        Intent intent = new Intent(NewAddress.this, MainActivity.class);
        startActivity(intent);
    }
}