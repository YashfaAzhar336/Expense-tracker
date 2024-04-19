package com.shahab12344.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class Income extends AppCompatActivity {
    Toolbar toolbar;
    Button addincome;
    String typeofincome="";
    EditText date,totalincome,enterincome;
    ImageView pickdate;
    String check;
    private DatabaseHelper databaseHelper;
    int year,month,day;
    String username;
    private Spinner incomesource;

    GridLayout gridLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        databaseHelper = new DatabaseHelper(this);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        incomesource = findViewById(R.id.incomeselection);
        addincome = findViewById(R.id.add_income);
        enterincome = findViewById(R.id.incomeamount);
        check = enterincome.getText().toString();
        date = findViewById(R.id.incomedate_et);
        pickdate = findViewById(R.id.incomedate);
        totalincome= findViewById(R.id.incometotal_et);
        final Calendar billingdate = Calendar.getInstance();
        date.setEnabled(false);
        date.setTextColor(Color.BLACK);
        totalincome.setEnabled(false);
        totalincome.setTextColor(Color.BLACK);




        //retrive data
         username = getIntent().getStringExtra("user");

        ArrayAdapter<CharSequence> incomeway = ArrayAdapter.createFromResource(this, R.array.income_selection,android.R.layout.simple_spinner_item);
        incomeway.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        incomesource.setAdapter(incomeway);

        incomesource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeofincome = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = billingdate.get(Calendar.YEAR);
                month = billingdate.get(Calendar.MONTH);
                day = billingdate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Income.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setText(i2+"/"+i1+"/"+i);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addIncomeRecord();
            }
        });
    }

    private void addIncomeRecord() {
        String incomeType = incomesource.getSelectedItem().toString();
        String dates = date.getText().toString().trim();
        String incomeAmountText = enterincome.getText().toString().trim();

        if (!incomeAmountText.isEmpty()) {
            double incomeAmount = Double.parseDouble(incomeAmountText);

            // Add income record to the database
            boolean success = databaseHelper.addIncome(incomeType, dates, incomeAmount, username);

            if (success) {
                Toast.makeText(this, "Income record added successfully", Toast.LENGTH_SHORT).show();
                clearFields();
            } else {
                Toast.makeText(this, "Failed to add income record", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void clearFields() {

        date.getText().clear();
        enterincome.getText().clear();
    }
    @Override
    protected void onResume() {
        super.onResume();
        double result = databaseHelper.calculateTotalIncome(username);
        totalincome.setText(String.valueOf(result));


    }
}