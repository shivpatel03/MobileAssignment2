package com.example.mobileassignment2;

public class Location {
    private int id;
    private String address;
    private double latitude;
    private double longitude;

    public Location(int id, String address, double latitude, double longitude){
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

    public double latitude() {
        return this.latitude;
    }

    public double longitude() {
        return this.longitude;
    }
}
