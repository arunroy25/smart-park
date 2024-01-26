package com.bd.smart_park;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ParkingEntryListActivity extends AppCompatActivity {

    DatabaseHelper helper;
    List<CarParking> dbList;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_entry_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        helper = new DatabaseHelper(this);
        dbList= new ArrayList<CarParking>();
        dbList = helper.getCarParkingDataFromDB();


        mRecyclerView = (RecyclerView)findViewById(R.id.mRecyclerView);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ParkingEntryRecyclerAdapter(this, dbList, new ParkingEntryRecyclerAdapter.deleteListener() {
            @Override
            public void onDelete() {
                Intent intent = new Intent(ParkingEntryListActivity.this, ParkingEntryListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton add = findViewById(R.id.mAddNew);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParkingEntryListActivity.this, ParkingEntryActivity.class);
                startActivity(intent);
                finish();
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
        Intent intent = new Intent(ParkingEntryListActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    //endregion

}
