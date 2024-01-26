package com.bd.smart_park;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;
    private static final String DATABASE_NAME="SmartPark";
    private static final int DATABASE_VERSION = 1;
    private static final String CAR_PARKING = "CarParking";
    private static final String PARKING_REPORT = "ParkingReport";

    private static final String PARKING_TABLE = "create table "+ CAR_PARKING +"(ParkingId INTEGER PRIMARY KEY AUTOINCREMENT,RFID TEXT,CarLicense TEXT,DriverName TEXT,EntryTime TEXT,ExitTime TEXT,ParkingCost TEXT,ParkingNote TEXT,UserId TEXT,DataEntryTime TEXT,DataModifyTime TEXT)";
    private static final String REPORTING_TABLE = "create table "+ PARKING_REPORT +
            "(ReportingId INTEGER PRIMARY KEY AUTOINCREMENT,RFID TEXT,CarLicense TEXT,DriverName TEXT,ReportingTime TEXT," +
            "ReportDetails TEXT,UserId TEXT,DataEntryTime TEXT,DataModifyTime TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PARKING_TABLE);
        db.execSQL(REPORTING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    /*Insert into database*/
    public void insertIntoCarParking(String RFID, String carLicense, String driverName, String entryTime, String exitTime,
                                     String parkingCost, String parkingNote, String userId, String dataEntryTime,
                                     String dataModifyTime){
        Log.d("duti", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value

        ContentValues values = new ContentValues();
        values.put("RFID", RFID);
        values.put("CarLicense", carLicense);
        values.put("DriverName", driverName);
        values.put("EntryTime", entryTime);
        values.put("ExitTime", exitTime);
        values.put("ParkingCost", parkingCost);
        values.put("ParkingNote", parkingNote);
        values.put("UserId", userId);
        values.put("DataEntryTime", dataEntryTime);
        values.put("DataModifyTime", dataModifyTime);

        // 3. insert
        db.insert(CAR_PARKING, null, values);
        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }

    public void insertIntoParkingReport(String RFID, String carLicense, String driverName, String reportingTime, String reportDetails,
                                     String userId, String dataEntryTime, String dataModifyTime){
        Log.d("duti", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value

        ContentValues values = new ContentValues();
        values.put("RFID", RFID);
        values.put("CarLicense", carLicense);
        values.put("DriverName", driverName);
        values.put("ReportingTime", reportingTime);
        values.put("ReportDetails", reportDetails);
        values.put("UserId", userId);
        values.put("DataEntryTime", dataEntryTime);
        values.put("DataModifyTime", dataModifyTime);

        // 3. insert
        db.insert(PARKING_REPORT, null, values);
        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }

    /* Get  data from database */
    public List<CarParking> getCarParkingDataFromDB(){
        List<CarParking> modelList = new ArrayList<CarParking>();
        String query = "select * from " + CAR_PARKING;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                CarParking model = new CarParking();
                model.setParkingId(cursor.getInt(0));
                model.setRFID(cursor.getString(1));
                model.setCarLicense(cursor.getString(2));
                model.setDriverName(cursor.getString(3));
                model.setEntryTime(cursor.getString(4));
                model.setExitTime(cursor.getString(5));
                model.setParkingCost(cursor.getString(6));
                model.setParkingNote(cursor.getString(7));
                model.setUserId(cursor.getString(8));
                model.setDataEntryTime(cursor.getString(9));
                model.setDataModifyTime(cursor.getString(10));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        Log.d("list data", modelList.toString());

        return modelList;
    }

    public List<ParkingReport> getParkingReportDataFromDB(){
        List<ParkingReport> modelList = new ArrayList<ParkingReport>();
        String query = "select * from " + PARKING_REPORT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ParkingReport model = new ParkingReport();
                model.setReportingId(cursor.getInt(0));
                model.setRFID(cursor.getString(1));
                model.setCarLicense(cursor.getString(2));
                model.setDriverName(cursor.getString(3));
                model.setReportingTime(cursor.getString(4));
                model.setReportDetails(cursor.getString(5));
                model.setUserId(cursor.getString(6));
                model.setDataEntryTime(cursor.getString(7));
                model.setDataModifyTime(cursor.getString(8));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        Log.d("list data", modelList.toString());

        return modelList;
    }


    /*delete a row from database*/
    public void deleteCarParking(int parkingId){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(CAR_PARKING, "ParkingId" + " = ?", new String[] {parkingId+""});
        db.close();
    }

    public void deleteParkingReporting(int reportingId){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(PARKING_REPORT, "ReportingId" + " = ?", new String[] {reportingId+""});
        db.close();
    }

}
