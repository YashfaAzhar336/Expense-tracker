package com.shahab12344.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Signup extends AppCompatActivity {
    public TextView login;
    EditText usernames,emails,passwords,cpasswords;
    private DatabaseHelper databaseHelper;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        databaseHelper = new DatabaseHelper(this);
        login = findViewById(R.id.loginhere);
        usernames = findViewById(R.id.signup_username_et);
        emails = findViewById(R.id.signup_email_et);
        passwords = findViewById(R.id.signup_password_et);
        cpasswords = findViewById(R.id.cpass);
        register = findViewById(R.id.register_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginpage = new Intent(Signup.this, Login.class);
                startActivity(loginpage);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emails.getText().toString().trim();
                String username = usernames.getText().toString().trim();
                String password = passwords.getText().toString().trim();
                String confirmPassword = cpasswords.getText().toString().trim();

                if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(Signup.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
               else if (!password.equals(confirmPassword)) {
                    Toast.makeText(Signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean userExists = databaseHelper.checkUserExists(username);
                    if (userExists) {
                        Toast.makeText(Signup.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean result = databaseHelper.addUser(email, username, password);
                        if (result) {
                            Toast.makeText(Signup.this, "Signup successful", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Signup.this, "Failed to signup", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
            }
}