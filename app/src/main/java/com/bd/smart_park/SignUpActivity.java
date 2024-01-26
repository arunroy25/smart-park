package com.bd.smart_park;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class SignUpActivity extends AppCompatActivity {

    private static final String PREF_NAME = "Smart_Park";
    int PRIVATE_MODE = 0;// shared pref mode
    EditText usernameET, confirmPasswordET;
    RelativeLayout relativeLayout;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        relativeLayout = findViewById(R.id.parent_container);
        usernameET = findViewById(R.id.Username);
        confirmPasswordET = findViewById(R.id.ConfirmPassword);
        progressDialog = new ProgressDialog(SignUpActivity.this);

        //declare a button and it's listener
        Button btn = findViewById(R.id.mRegisterBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to go to the next page
                if ((!TextUtils.isEmpty(usernameET.getText().toString())) && (!TextUtils.isEmpty(confirmPasswordET.getText().toString()))) {
                    if (isValidEmail()) {
                        if (isPasswordMatched()) {
                            progressDialog.setMessage(getText(R.string.login_working));
                            progressDialog.show();
                            saveUsername();
                            savePassword();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //this code will be execute after 1500 milliseconds
                                    progressDialog.dismiss();
                                    //code to go to the next page
                                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            }, 2000);
                        }
                    }
                } else {
                    Snackbar snackbar = Snackbar.make(relativeLayout, getText(R.string.please_enter_login), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        //declare a text view and it's listener
        TextView textView = findViewById(R.id.login_page);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to go to the next page
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    public void saveUsername(){
        String username = usernameET.getText().toString();
        SharedPreferences.Editor editor = this.getSharedPreferences(PREF_NAME, PRIVATE_MODE).edit();
        editor.putString("Username", username);
        editor.commit();
    }

    public void savePassword(){
        String confirmPassword = confirmPasswordET.getText().toString();
        SharedPreferences.Editor editor = this.getSharedPreferences(PREF_NAME, PRIVATE_MODE).edit();
        editor.putString("Password", confirmPassword);
        editor.commit();
    }

    public boolean isPasswordMatched(){
        EditText passwordET = findViewById(R.id.Password);
        String password = passwordET.getText().toString();
        String confirmPassword = confirmPasswordET.getText().toString();
        if(!password.isEmpty()){
            if(password.equals(confirmPassword)){
                return true;
            } else {
                Snackbar snackbar = Snackbar.make(relativeLayout, getText(R.string.password_not_matched), Snackbar.LENGTH_LONG);
                snackbar.show();
                return false;
            }
        } else {
            Snackbar snackbar = Snackbar.make(relativeLayout, getText(R.string.please_enter_password), Snackbar.LENGTH_LONG);
            snackbar.show();
            return false;
        }
    }

    public boolean isValidEmail() {
        EditText emailET = findViewById(R.id.Email);
        String email = emailET.getText().toString();
        if(!TextUtils.isEmpty(email)){
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Snackbar snackbar = Snackbar.make(relativeLayout, getText(R.string.email_vaild), Snackbar.LENGTH_LONG);
                snackbar.show();
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}
