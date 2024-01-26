package com.bd.smart_park;

public class ParkingReport {

    public int ReportingId;
    public String RFID;
    public String CarLicense;
    public String DriverName;
    public String ReportingTime;
    public String ReportDetails;
    public String UserId;
    public String DataEntryTime;
    public String DataModifyTime;

    public ParkingReport(String RFID, String carLicense, String driverName, String reportingTime, String reportDetails, String userId, String dataEntryTime, String dataModifyTime) {
        this.RFID = RFID;
        CarLicense = carLicense;
        DriverName = driverName;
        ReportingTime = reportingTime;
        ReportDetails = reportDetails;
        UserId = userId;
        DataEntryTime = dataEntryTime;
        DataModifyTime = dataModifyTime;
    }

    public ParkingReport() {
    }

    public int getReportingId() {
        return ReportingId;
    }

    public void setReportingId(int reportingId) {
        ReportingId = reportingId;
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

    public String getReportingTime() {
        return ReportingTime;
    }

    public void setReportingTime(String reportingTime) {
        ReportingTime = reportingTime;
    }

    public String getReportDetails() {
        return ReportDetails;
    }

    public void setReportDetails(String reportDetails) {
        ReportDetails = reportDetails;
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
