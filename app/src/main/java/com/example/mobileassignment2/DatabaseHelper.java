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
        super(context, DATABASE_NAME, null, 2);
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

        insertDefaultData(db);
    }

    private void insertDefaultData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        String[] addresses = {
                "123 Queen St W", "456 King St W", "789 Dundas St E", "101 Bay St", "202 College St",
                "303 Yonge St", "404 Front St W", "505 Spadina Ave", "606 Bathurst St", "707 St Clair Ave W",
                "808 Bloor St W", "909 Finch Ave W", "123 King St E", "234 Queen St E", "345 College St",
                "456 Church St", "567 Dundas St W", "678 Yonge St", "789 Bay St", "890 Gerrard St E",
                "1010 College St W", "1111 Queen St E", "1212 Bloor St E", "1313 Dufferin St", "1414 Wilson Ave",
                "1515 St Clair Ave E", "1616 Kingston Rd", "1717 Bathurst St", "1818 Lawrence Ave W", "1919 Sheppard Ave E",
                "2020 Finch Ave E", "2121 Eglinton Ave W", "2222 Bayview Ave", "2323 Gerrard St W", "2424 Danforth Ave",
                "2525 Yonge St", "2626 Keele St", "2727 Dupont St", "2828 Jane St", "2929 Midland Ave",
                "3030 Woodbine Ave", "3131 Lawrence Ave E", "3232 Morningside Ave", "3333 Markham Rd", "3434 Finch Ave E",
                "3535 Ellesmere Rd", "3636 Runnymede Rd", "3737 Dufferin St", "3838 Finch Ave W", "3939 Bathurst St",
                "4040 Queen St E", "4141 St Clair Ave W", "4242 Lawrence Ave W", "4343 Victoria Park Ave", "4444 Avenue Rd",
                "4545 Gerrard St E", "4646 Yonge St", "4747 College St", "4848 Kingston Rd", "4949 Bloor St W",
                "5050 Bathurst St", "5151 Eglinton Ave W", "5252 Yonge St", "5353 Bayview Ave", "5454 Finch Ave W",
                "5555 Jane St", "5656 Lawrence Ave W", "5757 Dupont St", "5858 St Clair Ave E", "5959 Keele St",
                "6060 Eglinton Ave W", "6161 Queen St E", "6262 College St", "6363 Church St", "6464 Dufferin St",
                "6565 Spadina Ave", "6666 Bloor St E", "6767 Victoria Park Ave", "6868 Yonge St", "6969 St Clair Ave W",
                "7070 Dundas St W", "7171 Dupont St", "7272 Bathurst St", "7373 Markham Rd", "7474 Bayview Ave",
                "7575 Eglinton Ave E", "7676 Keele St", "7777 College St", "7878 Gerrard St E", "7979 Queen St W",
                "8080 Finch Ave E", "8181 Danforth Ave", "8282 Wilson Ave", "8383 Bathurst St", "8484 Lawrence Ave E",
                "8585 Woodbine Ave", "8686 Dupont St", "8787 Dufferin St", "8888 Bloor St E", "8989 St Clair Ave W",
                "9090 Jane St", "9191 Spadina Ave", "9292 Keele St", "9393 Bathurst St", "9494 College St W",
                "9595 Yonge St", "9696 Church St", "9797 Finch Ave W", "9898 Lawrence Ave W", "9999 Gerrard St W",
                "10000 Bayview Ave", "10101 Victoria Park Ave", "10202 Avenue Rd", "10303 Dundas St W", "10404 Bloor St W"
        };

        double[] latitudes = {
                43.6547, 43.6451, 43.6568, 43.6459, 43.6629, 43.6536, 43.6419, 43.6492, 43.6622, 43.6867,
                43.6645, 43.7384, 43.6534, 43.6548, 43.6528, 43.6599, 43.6539, 43.6595, 43.6500, 43.6699,
                43.6570, 43.6655, 43.6715, 43.6543, 43.6631, 43.6689, 43.6772, 43.6829, 43.6891, 43.7058,
                43.6931, 43.7046, 43.7081, 43.7228, 43.7362, 43.7444, 43.7457, 43.7511, 43.7591, 43.7615,
                43.7653, 43.7778, 43.7860, 43.7929, 43.8034, 43.8159, 43.8282, 43.8383, 43.8421, 43.8519,
                43.8622, 43.8729, 43.8787, 43.8860, 43.8934, 43.9011, 43.9056, 43.9113, 43.9185, 43.9243,
                43.9312, 43.9370, 43.9445, 43.9500, 43.9567, 43.9631, 43.9705, 43.9780, 43.9837, 43.9910,
                43.9981, 44.0039, 44.0106, 44.0170, 44.0223, 44.0284, 44.0339, 44.0411, 44.0480, 44.0562,
                44.0623, 44.0694, 44.0753, 44.0819, 44.0880, 44.0931, 44.0993, 44.1065, 44.1136, 44.1208
        };

        double[] longitudes = {
                -79.3803, -79.3891, -79.3523, -79.3807, -79.3950, -79.3832, -79.3862, -79.4023, -79.4104, -79.4239,
                -79.3970, -79.4180, -79.3821, -79.3884, -79.3930, -79.3812, -79.3785, -79.3947, -79.3837, -79.3916,
                -79.3846, -79.3793, -79.3876, -79.3965, -79.3969, -79.3888, -79.3769, -79.3815, -79.3856, -79.3892,
                -79.3894, -79.3851, -79.3922, -79.3889, -79.3949, -79.3896, -79.3872, -79.3805, -79.3829, -79.3777,
                -79.3790, -79.3842, -79.3864, -79.3877, -79.3889, -79.3903, -79.3915, -79.3919, -79.3931, -79.3942,
                -79.3950, -79.3964, -79.3969, -79.3981, -79.3995, -79.3997, -79.4009, -79.4012, -79.4023, -79.4031,
                -79.4039, -79.4050, -79.4057, -79.4062, -79.4071, -79.4076, -79.4080, -79.4094, -79.4101, -79.4115,
                -79.4121, -79.4125, -79.4130, -79.4135, -79.4142, -79.4148, -79.4152, -79.4157, -79.4162, -79.4166,
                -79.4170, -79.4174, -79.4177, -79.4182, -79.4186, -79.4190, -79.4193, -79.4197, -79.4201, -79.4205
        };

        for (int i = 0; i < addresses.length; i++) {
            values.put("ADDRESS", addresses[i]);
            values.put("LATITUDE", latitudes[i]);
            values.put("LONGITUDE", longitudes[i]);
            db.insert(TABLE_NAME, null, values);
            values.clear();
        }
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
