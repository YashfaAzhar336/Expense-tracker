package com.shahab12344.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class Expense extends AppCompatActivity {
    EditText date,forexpensetype,expenseamount;
    Button expenseadd;
    ImageView pickdate;
    private DatabaseHelper databaseHelper;
    int year,month,day;
    String username;
    Toolbar toolbar;

    GridLayout gridLayoutexpense;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        databaseHelper = new DatabaseHelper(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        date = findViewById(R.id.expensedate);
        forexpensetype = findViewById(R.id.expensetype_et);
        expenseamount = findViewById(R.id.expenseamount_et);
        forexpensetype.setEnabled(false);
        forexpensetype.setTextColor(Color.BLACK);
        date.setEnabled(false);
        date.setTextColor(Color.BLACK);
        expenseadd = findViewById(R.id.add_expense_btn);
        gridLayoutexpense = findViewById(R.id.gridexpense);
        pickdate = findViewById(R.id.calender);
        final Calendar c = Calendar.getInstance();
        //retrive data
        username = getIntent().getStringExtra("user");



        setSingleEvent(gridLayoutexpense);

        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Expense.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setText(i2+"/"+i1+"/"+i);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


      //Button click
        expenseadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addExpenseRecord();
            }
        });
    }

//    add expense
private void addExpenseRecord() {
    String expenseType = forexpensetype.getText().toString();
    String dates = date.getText().toString().trim();
    String expenseAmountText = expenseamount.getText().toString().trim();

    if(expenseType.isEmpty() || dates.isEmpty() || expenseAmountText.isEmpty()){
        Toast.makeText(this, "Please Fill all fields", Toast.LENGTH_SHORT).show();
    }
    if(!expenseAmountText.isEmpty()) {
        double ExpenseAmount = Double.parseDouble(expenseAmountText);

    // Add income record to the database
    boolean success = databaseHelper.addExpense(expenseType, dates, ExpenseAmount, username);

    if (success) {
        Toast.makeText(this, "Expense record added successfully", Toast.LENGTH_SHORT).show();
        clearFields();
    } else {
        Toast.makeText(this, "Failed to add Expense record", Toast.LENGTH_SHORT).show();
    }}
}

    private void clearFields() {
        forexpensetype.getText().clear();
        date.getText().clear();
        expenseamount.getText().clear();
    }


    private void setSingleEvent(GridLayout gridLayoutexpense) {
        for(int i = 0; i < gridLayoutexpense.getChildCount(); i++){
            CardView card = (CardView) gridLayoutexpense.getChildAt(i);
            final int position = i;

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(position == 0){
                        forexpensetype.setText("Clothes");
                    }
                    else  if(position == 1){
                        forexpensetype.setText("Food");
                    }

                    else if(position == 2){
                        forexpensetype.setText("Trip");
                    }

                    else if(position == 3){
                        forexpensetype.setText("Travelling");
                    }
                    else if(position == 4){
                        forexpensetype.setText("Gym");
                    }
                    else if(position == 5){
                        forexpensetype.setText("Medical");
                    }
                }
            });




        }


    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        double a = databaseHelper.calculateTotalexpense(username);
//        expenseamount.setText(String.valueOf(a));
//    }
}