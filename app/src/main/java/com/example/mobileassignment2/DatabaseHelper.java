package com.example.mobileassignment2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LOCATION_DB";
    private static final String TABLE_NAME = "LOCATIONS";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "ADDRESS";
    private static final String COL_3 = "LATITUDE";
    private static final String COL_4 = "LONGITUDE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "ADDRESS VARCHAR(255) NOT NULL, " +
                        "LATITUDE DECIMAL(9,6) NOT NULL, " +
                        "LONGITUDE DECIMAL(9,6) NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @SuppressLint("Range")
    public List<Location> getEntries() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        List<Location> locations = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COL_1));
                String address = cursor.getString(cursor.getColumnIndex(COL_2));
                double latitude = cursor.getDouble(cursor.getColumnIndex(COL_3));
                double longitude = cursor.getDouble(cursor.getColumnIndex(COL_4));

                Location location = new Location(id, address, longitude, latitude);
                locations.add(location);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return locations;
    }

    public void addEntry(String address, double longitude, double latitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_2, address);
        values.put(COL_3, latitude);
        values.put(COL_4, longitude);

        db.insert(TABLE_NAME, null, values);

    }

    public void deleteEntry(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "ID = ?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    public List<Location> search(String searchTerm){
        List<Location> locationList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE LOWER(" + COL_2 + ") LIKE LOWER(?)";
        Cursor cursor = db.rawQuery(query, new String[]{"%" + searchTerm + "%"});

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COL_1));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(COL_2));
                double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_3));
                double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_4));
                locationList.add(new Location(id, address, longitude, latitude));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return locationList;
    }

    public boolean updateEntry(int id, String address, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_2, address);
        values.put(COL_3, latitude);
        values.put(COL_4, longitude);

        int rowsUpdated = db.update(TABLE_NAME, values, "ID = ?", new String[]{String.valueOf(id)});
        return rowsUpdated > 0;
    }

}
