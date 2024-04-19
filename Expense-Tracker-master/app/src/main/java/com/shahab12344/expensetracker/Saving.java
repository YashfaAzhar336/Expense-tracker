package com.shahab12344.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Saving extends AppCompatActivity {
    Toolbar toolbar;
    Button home;
    String usernames;
    private DatabaseHelper databaseHelper;
    EditText t_income, t_expense, t_payeddebit, total_saving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);
        //onResume();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        t_income = findViewById(R.id.savingincome_et);
        t_expense = findViewById(R.id.savomgexpense_et);
        t_payeddebit = findViewById(R.id.savingdebit_et);
        total_saving = findViewById(R.id.TotalSaving_et);
        t_income.setEnabled(false);
        t_income.setTextColor(Color.BLACK);
        t_expense.setEnabled(false);
        t_expense.setTextColor(Color.BLACK);
        t_payeddebit.setEnabled(false);
        t_payeddebit.setTextColor(Color.BLACK);
        total_saving.setEnabled(false);
        total_saving.setTextColor(Color.BLACK);
        databaseHelper = new DatabaseHelper(this);
        //retrive data
        usernames = getIntent().getStringExtra("user");





    }

    @Override
    protected void onResume() {
        super.onResume();
        double exoense = databaseHelper.calculateTotalexpense(usernames);
        double debit = databaseHelper.calculateTotaldebit(usernames);
        t_expense.setText(String.valueOf(exoense));
        t_payeddebit.setText(String.valueOf(debit));

        double total = exoense + debit;
        double totalIncome = databaseHelper.calculateTotalIncome(usernames);
        t_income.setText(String.valueOf(totalIncome));
        double sum = totalIncome - total;


        if(sum < 0){
            total_saving.setTextColor(Color.RED);
            total_saving.setText(String.valueOf(sum));
        }
        else{
            total_saving.setTextColor(Color.parseColor("#FF018786"));
            total_saving.setText(String.valueOf(sum));
        }
    }
}