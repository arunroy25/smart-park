package com.bd.smart_park;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String PREF_NAME = "Smart_Park";
    int PRIVATE_MODE = 0;// shared pref mode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //thread to make delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //this code will be execute after 1500 milliseconds
                //code to go to the next page
                if (isLoggedIn()) {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1500);

    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = SplashActivity.this.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return sharedPreferences.getBoolean("IsLoggedIn", false);
    }

}
