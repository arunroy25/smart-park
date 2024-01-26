package com.bd.smart_park;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private static final String PREF_NAME = "Smart_Park";
    int PRIVATE_MODE = 0;// shared pref mode
    EditText usernameET, passwordET;
    RelativeLayout relativeLayout;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        relativeLayout = findViewById(R.id.parent_container);
        usernameET = findViewById(R.id.Username);
        passwordET = findViewById(R.id.Password);
        progressDialog = new ProgressDialog(LoginActivity.this);


        //declare a button and it's listener
        Button btn = findViewById(R.id.mLoginBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to go to the next page
                if ((!TextUtils.isEmpty(usernameET.getText().toString())) && (!TextUtils.isEmpty(passwordET.getText().toString()))) {
                    if (isLoginValid()) {
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
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        }, 2000);
                    }
                } else {
                    Snackbar snackbar = Snackbar.make(relativeLayout, getText(R.string.please_enter_login), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        //declare a text view and it's listener
        TextView textView = findViewById(R.id.register_page);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to go to the next page
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    public void setLoginStatus(){
        SharedPreferences.Editor editor = this.getSharedPreferences(PREF_NAME, PRIVATE_MODE).edit();
        editor.putBoolean("IsLoggedIn", true);
        editor.commit();
    }

    public boolean isLoginValid(){
        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        String storedUsername = sharedPreferences.getString("Username", "");
        String storedPassword = sharedPreferences.getString("Password", "");
        if((storedUsername.equals(usernameET.getText().toString())) && (storedPassword.equals(passwordET.getText().toString()))){
            return true;
        } else {
            Snackbar snackbar = Snackbar.make(relativeLayout, getText(R.string.please_enter_login), Snackbar.LENGTH_LONG);
            snackbar.show();
            return false;
        }
    }
}