package com.shahab12344.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class all_wishlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<model> dataholder;
    String username;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_wishlist);
        recyclerView = findViewById(R.id.recyclerwish);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //retrive data
        username = getIntent().getStringExtra("user");
        dataholder=new ArrayList<>();

        Cursor cursor = new DatabaseHelper(this).getAllwishByUser(username);

        while (cursor.moveToNext()){
            model obj = new model(cursor.getString(1),cursor.getString(2),cursor.getDouble(3));
            dataholder.add(obj);
        }
        myadapterwish myadapters = new myadapterwish(dataholder);
        recyclerView.setAdapter(myadapters);
    }
}