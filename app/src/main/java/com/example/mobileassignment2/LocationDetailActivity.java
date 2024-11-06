package com.example.mobileassignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LocationDetailActivity extends AppCompatActivity {

    private TextView addressTextView;
    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private EditText addressEditText;
    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private Button returnToMainButton;
    private Button editButton;
    private Button saveButton;
    private Button cancelButton;
    private DatabaseHelper dbHelper;
    private int id;

    private String originalAddress;
    private double originalLatitude;
    private double originalLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        dbHelper = new DatabaseHelper(this);

        addressTextView = findViewById(R.id.addressTextView);
        latitudeTextView = findViewById(R.id.latitudeTextView);
        longitudeTextView = findViewById(R.id.longitudeTextView);
        addressEditText = findViewById(R.id.addressEditText);
        latitudeEditText = findViewById(R.id.latitudeEditText);
        longitudeEditText = findViewById(R.id.longitudeEditText);
        returnToMainButton = findViewById(R.id.returnToMainButton);
        editButton = findViewById(R.id.editButton);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        // data from intent
        id = getIntent().getIntExtra("addressId", -1);
        String address = getIntent().getStringExtra("address");
        double latitude = getIntent().getDoubleExtra("latitude", 0.0);
        double longitude = getIntent().getDoubleExtra("longitude", 0.0);

        // sent data to existing views
        addressTextView.setText(String.format("Address: %s", address));
        latitudeTextView.setText(String.format("Latitude: %s", latitude));
        longitudeTextView.setText(String.format("Longitude: %s", longitude));

        // keep original values for later
        originalAddress = address;
        originalLatitude = latitude;
        originalLongitude = longitude;

        // set the text on the edit fields when they become visible
        addressEditText.setText(address);
        latitudeEditText.setText(String.valueOf(latitude));
        longitudeEditText.setText(String.valueOf(longitude));

        // hide all the edit fields initially
        addressEditText.setVisibility(View.GONE);
        latitudeEditText.setVisibility(View.GONE);
        longitudeEditText.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
    }

    // simply swap from 'view' mode to 'edit' mode. it makes all the edit fields visible
    public void toggleEditMode(View view) {
        if (addressTextView.getVisibility() == View.VISIBLE) {
            addressTextView.setVisibility(View.GONE);
            latitudeTextView.setVisibility(View.GONE);
            longitudeTextView.setVisibility(View.GONE);
            returnToMainButton.setVisibility(View.GONE);

            addressEditText.setVisibility(View.VISIBLE);
            latitudeEditText.setVisibility(View.VISIBLE);
            longitudeEditText.setVisibility(View.VISIBLE);

            saveButton.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.VISIBLE);
            editButton.setVisibility(View.GONE); // Hide Edit button
        }
    }

    public void cancelEdit(View view) {
        // revert to original values (saved before)
        addressEditText.setText(originalAddress);
        latitudeEditText.setText(String.valueOf(originalLatitude));
        longitudeEditText.setText(String.valueOf(originalLongitude));

        // swap back to 'view' mode, hide all edit fields
        addressTextView.setVisibility(View.VISIBLE);
        latitudeTextView.setVisibility(View.VISIBLE);
        longitudeTextView.setVisibility(View.VISIBLE);
        addressEditText.setVisibility(View.GONE);
        latitudeEditText.setVisibility(View.GONE);
        longitudeEditText.setVisibility(View.GONE);

        saveButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
        editButton.setVisibility(View.VISIBLE);
        returnToMainButton.setVisibility(View.VISIBLE);
    }


    public void saveChanges(View view) {
        String updatedAddress = addressEditText.getText().toString();
        double updatedLatitude = Double.parseDouble(latitudeEditText.getText().toString());
        double updatedLongitude = Double.parseDouble(longitudeEditText.getText().toString());

        // update the database using it's method
        boolean isUpdated = dbHelper.updateEntry(id, updatedAddress, updatedLatitude, updatedLongitude);

        if (isUpdated) {
            addressTextView.setText(updatedAddress);
            latitudeTextView.setText(String.valueOf(updatedLatitude));
            longitudeTextView.setText(String.valueOf(updatedLongitude));

            // switch views (ONLY if the database gets updated
            addressTextView.setVisibility(View.VISIBLE);
            latitudeTextView.setVisibility(View.VISIBLE);
            longitudeTextView.setVisibility(View.VISIBLE);

            addressEditText.setVisibility(View.GONE);
            latitudeEditText.setVisibility(View.GONE);
            longitudeEditText.setVisibility(View.GONE);

            saveButton.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);
            editButton.setVisibility(View.VISIBLE);
            returnToMainButton.setVisibility(View.VISIBLE);
        }
    }

    // delete with given id and return back to main page
    public void deleteAddress(View view) {
        if (id != -1) {
            dbHelper.deleteEntry(id);
        }
        Intent intent = new Intent(LocationDetailActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void returnToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
