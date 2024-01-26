package com.bd.smart_park;

public class CarParking {

    public int ParkingId;
    public String RFID;
    public String CarLicense;
    public String DriverName;
    public String EntryTime;
    public String ExitTime;
    public String ParkingCost;
    public String ParkingNote;
    public String UserId;
    public String DataEntryTime;
    public String DataModifyTime;

    public CarParking(String RFID, String carLicense, String driverName, String entryTime, String exitTime, String parkingCost, String parkingNote, String userId, String dataEntryTime, String dataModifyTime) {
        this.RFID = RFID;
        CarLicense = carLicense;
        DriverName = driverName;
        EntryTime = entryTime;
        ExitTime = exitTime;
        ParkingCost = parkingCost;
        ParkingNote = parkingNote;
        UserId = userId;
        DataEntryTime = dataEntryTime;
        DataModifyTime = dataModifyTime;
    }

    public CarParking() {

    }

    public int getParkingId() {
        return ParkingId;
    }

    public void setParkingId(int parkingId) {
        ParkingId = parkingId;
    }

    public String getRFID() {
        return RFID;
    }

    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

    public String getCarLicense() {
        return CarLicense;
    }

    public void setCarLicense(String carLicense) {
        CarLicense = carLicense;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public String getEntryTime() {
        return EntryTime;
    }

    public void setEntryTime(String entryTime) {
        EntryTime = entryTime;
    }

    public String getExitTime() {
        return ExitTime;
    }

    public void setExitTime(String exitTime) {
        ExitTime = exitTime;
    }

    public String getParkingCost() {
        return ParkingCost;
    }

    public void setParkingCost(String parkingCost) {
        ParkingCost = parkingCost;
    }

    public String getParkingNote() {
        return ParkingNote;
    }

    public void setParkingNote(String parkingNote) {
        ParkingNote = parkingNote;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getDataEntryTime() {
        return DataEntryTime;
    }

    public void setDataEntryTime(String dataEntryTime) {
        DataEntryTime = dataEntryTime;
    }

    public String getDataModifyTime() {
        return DataModifyTime;
    }

    public void setDataModifyTime(String dataModifyTime) {
        DataModifyTime = dataModifyTime;
    }
}
