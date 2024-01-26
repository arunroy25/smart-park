package com.bd.smart_park;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

    private static final String PREF_NAME = "Smart_Park";
    int PRIVATE_MODE = 0;// shared pref mode
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final RelativeLayout relativeLayout = findViewById(R.id.parent);
        progressDialog = new ProgressDialog(HomeActivity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getText(R.string.app_name));
        setSupportActionBar(toolbar);

        CardView cardView1 = findViewById(R.id.add);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ParkingEntryActivity.class);
                startActivity(intent);
                finish();
            }
        });

        CardView cardView2 = findViewById(R.id.release);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(relativeLayout, getText(R.string.release) + " is coming soon...", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        CardView cardView3 = findViewById(R.id.history);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ParkingEntryListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        CardView cardView4 = findViewById(R.id.report);
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ParkingReportListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                progressDialog.setMessage(getText(R.string.login_working));
                progressDialog.show();
                //thread to make delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //this code will be execute after 1500 milliseconds
                        setLoginStatus();
                        progressDialog.dismiss();
                        //code to go to the next page
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setLoginStatus(){
        SharedPreferences.Editor editor = this.getSharedPreferences(PREF_NAME, PRIVATE_MODE).edit();
        editor.putBoolean("IsLoggedIn", false);
        editor.commit();
    }
}
