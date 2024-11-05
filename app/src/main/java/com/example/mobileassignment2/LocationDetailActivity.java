package com.example.mobileassignment2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LocationDetailActivity extends AppCompatActivity {

    private TextView addressTextView;
    private TextView latitudeTextView;
    private TextView longitudeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        addressTextView = findViewById(R.id.addressTextView);
        latitudeTextView = findViewById(R.id.latitudeTextView);
        longitudeTextView = findViewById(R.id.longitudeTextView);

        // Get data from intent
        String address = getIntent().getStringExtra("address");
        double latitude = getIntent().getDoubleExtra("latitude", 0.0);
        double longitude = getIntent().getDoubleExtra("longitude", 0.0);

        // Set data to TextViews
        addressTextView.setText(address);
        latitudeTextView.setText(String.valueOf(latitude));
        longitudeTextView.setText(String.valueOf(longitude));
    }
}
