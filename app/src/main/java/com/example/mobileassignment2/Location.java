package com.example.mobileassignment2;

public class Location {
    private int id;
    private String address;
    private double latitude;
    private double longitude;

    public Location(int id, String address, double longitude, double latitude){
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return this.id;
    }

    public String getAddress() {
        return this.address;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
