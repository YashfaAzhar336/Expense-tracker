package com.shahab12344.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private Button loginButton;
    private EditText name, passwords;
    private DatabaseHelper databaseHelper;
    private TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.login_username_et);
        passwords = findViewById(R.id.login_password_et);
        databaseHelper = new DatabaseHelper(this);



        loginButton = findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString().trim();
                String password = passwords.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                } else {
                    boolean result = databaseHelper.checkUser(username, password);
                    if (result) {
                        Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();

                        Intent dashboard = new Intent(Login.this, MainActivity.class);
                        dashboard.putExtra("userlog",username);
                        startActivity(dashboard);
                    } else {
                        Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup = findViewById(R.id.siguphere);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signpage = new Intent(Login.this, Signup.class);
                startActivity(signpage);
            }
        });
    }
}