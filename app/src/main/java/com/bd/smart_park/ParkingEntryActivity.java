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

public class ParkingEntryActivity extends AppCompatActivity {

    EditText CarLicense,DriverName,EntryTime,ExitTime,ParkingCost,ParkingNote;
    FloatingActionButton save;
    DatabaseHelper helper;
    List<CarParking> dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_entry);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbList= new ArrayList<CarParking>();
        CarLicense = (EditText)findViewById(R.id.CarLicense);
        DriverName = (EditText)findViewById(R.id.DriverName);
        EntryTime =(EditText)findViewById(R.id.EntryTime);
        ExitTime = (EditText)findViewById(R.id.ExitTime);
        ParkingCost = (EditText)findViewById(R.id.ParkingCost);
        ParkingNote = (EditText)findViewById(R.id.ParkingNote);
        save  =(FloatingActionButton) findViewById(R.id.mSaveMaster);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carLicense = CarLicense.getText().toString();
                String driverName = DriverName.getText().toString();
                String entryTime = EntryTime.getText().toString();
                String exitTime = ExitTime.getText().toString();
                String parkingCost = ParkingCost.getText().toString();
                String parkingNote = ParkingNote.getText().toString();

                if(carLicense.equals("") || driverName.equals("") || entryTime.equals("") || parkingNote.equals("")){
                    Toast.makeText(ParkingEntryActivity.this,"Please fill all the input",Toast.LENGTH_LONG).show();
                } else {
                    helper = new DatabaseHelper(ParkingEntryActivity.this);
                    helper.insertIntoCarParking("", carLicense, driverName, entryTime, exitTime, parkingCost, parkingNote, "", "", "");

                    Intent intent = new Intent(ParkingEntryActivity.this, ParkingEntryListActivity.class);
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
        Intent intent = new Intent(ParkingEntryActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    //endregion

}
