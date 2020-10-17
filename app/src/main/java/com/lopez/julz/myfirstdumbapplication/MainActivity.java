package com.lopez.julz.myfirstdumbapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    public EditText username, password;
    public Button loginButton;

    public String[][] users = {
            {"julz", "lopez"},
            {"juan", "juan123"},
            {"asus", "asusphone"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUserExist(username.getText().toString(), password.getText().toString())) {
                    Intent nextActivityIntent = new Intent(MainActivity.this, HomePage.class);
                    startActivity(nextActivityIntent);
                } else {
                    Snackbar.make(view, "Username and password doesn't exist idiot.", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean isUserExist(String username, String password) {
        try {
            boolean isExists = false;
            for (int i=0; i<users.length; i++) {
                if (username.equals(users[i][0]) && password.equals(users[i][1])) {
                    isExists = true;
                    break;
                }
            }
            return isExists;
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
            return false;
        }
    }
}