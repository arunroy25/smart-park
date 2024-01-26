package com.bd.smart_park;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ReportingEntryActivity extends AppCompatActivity {

    EditText CarLicense,DriverName,ReportingTime,ReportDetails;
    FloatingActionButton save;
    DatabaseHelper helper;
    List<CarParking> dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_entry);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbList= new ArrayList<CarParking>();
        CarLicense = (EditText)findViewById(R.id.CarLicense);
        DriverName = (EditText)findViewById(R.id.DriverName);
        ReportingTime =(EditText)findViewById(R.id.ReportingTime);
        ReportDetails = (EditText)findViewById(R.id.ReportDetails);
        save  =(FloatingActionButton) findViewById(R.id.mSaveMaster);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carLicense = CarLicense.getText().toString();
                String driverName = DriverName.getText().toString();
                String reportingTime = ReportingTime.getText().toString();
                String reportDetails = ReportDetails.getText().toString();

                if(carLicense.equals("") || driverName.equals("") || reportingTime.equals("") || reportDetails.equals("")){
                    Toast.makeText(ReportingEntryActivity.this,"Please fill all the input",Toast.LENGTH_LONG).show();
                } else {
                    helper = new DatabaseHelper(ReportingEntryActivity.this);
                    helper.insertIntoParkingReport("", carLicense, driverName, reportingTime, reportDetails, "", "", "");

                    Intent intent = new Intent(ReportingEntryActivity.this, ParkingReportListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    //region back button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ReportingEntryActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    //endregion

}
