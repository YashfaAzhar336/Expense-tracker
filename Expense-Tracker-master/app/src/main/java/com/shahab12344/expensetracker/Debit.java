package com.shahab12344.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
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

public class Debit extends AppCompatActivity {
    Toolbar toolbar;
    Spinner billtype;
    Button paydebit, pendingbill;
    String username;
    EditText date,debitamount;
    private DatabaseHelper databaseHelper;
    ImageView pickdate;
    int year,month,day;

    GridLayout gridLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        billtype = findViewById(R.id.billselection);
        date = findViewById(R.id.billdate);
        pickdate = findViewById(R.id.billdateselection);
        final Calendar billingdate = Calendar.getInstance();
        date.setEnabled(false);
        date.setTextColor(Color.BLACK);
        debitamount = findViewById(R.id.billamountet);
        pendingbill = findViewById(R.id.pendingbill_btn);
        paydebit = findViewById(R.id.add_bill);
        databaseHelper = new DatabaseHelper(this);
        //retrive data
        username = getIntent().getStringExtra("user");




        ArrayAdapter<CharSequence> incomeway = ArrayAdapter.createFromResource(this, R.array.bill, android.R.layout.simple_spinner_item);
        incomeway.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        billtype.setAdapter(incomeway);

        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = billingdate.get(Calendar.YEAR);
                month = billingdate.get(Calendar.MONTH);
                day = billingdate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Debit.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setText(i2+"/"+i1+"/"+i);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        paydebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDebitRecord();
            }
        });

        pendingbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addpendingRecord();
            }
        });

    }

    private void addDebitRecord() {
        String debitType = billtype.getSelectedItem().toString();
        String dates = date.getText().toString().trim();
        String debitAmountText = debitamount.getText().toString().trim();

        if (dates.isEmpty() || debitAmountText.isEmpty()) {
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        }

        if (!debitAmountText.isEmpty()) {
            double debitamount = Double.parseDouble(debitAmountText);

            // Add income record to the database
            boolean success = databaseHelper.addDebit(debitType, dates, debitamount, username);

            if (success) {
                Toast.makeText(this, "Debit record added successfully", Toast.LENGTH_SHORT).show();
                clearFields();
            } else {
                Toast.makeText(this, "Failed to add Debit record", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addpendingRecord() {
        String debitType = billtype.getSelectedItem().toString();
        String dates = date.getText().toString().trim();
        String debitAmountText = debitamount.getText().toString().trim();

        if (dates.isEmpty() || debitAmountText.isEmpty()) {
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        }

        if (!debitAmountText.isEmpty()) {
            double debitamount = Double.parseDouble(debitAmountText);

            // Add income record to the database
            boolean success = databaseHelper.addpending(debitType, dates, debitamount, username);

            if (success) {
                Toast.makeText(this, "Pending record added successfully", Toast.LENGTH_SHORT).show();
                clearFields();
            } else {
                Toast.makeText(this, "Failed to add Pending record", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void clearFields() {

        date.getText().clear();
        debitamount.getText().clear();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}