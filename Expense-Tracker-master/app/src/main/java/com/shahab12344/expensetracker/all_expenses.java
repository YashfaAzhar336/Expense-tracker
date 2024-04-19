package com.shahab12344.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class all_expenses extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<model> dataholder;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expenses);
        recyclerView = findViewById(R.id.recyclerexpense);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //retrive data
        username = getIntent().getStringExtra("user");
        dataholder=new ArrayList<>();

        Cursor cursor = new DatabaseHelper(this).getAllexpenseByUser(username);

        while (cursor.moveToNext()){
            model obj = new model(cursor.getString(1),cursor.getString(2),cursor.getDouble(3));
            dataholder.add(obj);
        }
        myadapterexpense myadapters = new myadapterexpense(dataholder);
        recyclerView.setAdapter(myadapters);
    }
}