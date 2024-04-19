package com.shahab12344.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Wishlist extends AppCompatActivity {
    Toolbar toolbar;
    EditText selectedwish, wishamount;
    Spinner selectwishfav;
    String username;

    private DatabaseHelper databaseHelper;
    Button addwish;
    GridLayout wishlayoutgrid;
    ActionBarDrawerToggle toggle;
    ArrayAdapter<CharSequence> Wishlistadp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        wishlayoutgrid = findViewById(R.id.wishgrid);
        selectedwish = findViewById(R.id.wishtype_et);
        selectedwish.setEnabled(false);
        selectedwish.setTextColor(Color.BLACK);
        selectwishfav = findViewById(R.id.selectspecific);
        wishamount = findViewById(R.id.estimateamount_et);
        addwish = findViewById(R.id.add_wish_btn);
        databaseHelper = new DatabaseHelper(this);
        //retrive data
        username = getIntent().getStringExtra("user");


        setSingleEvent(wishlayoutgrid);


        addwish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addwishlistRecord();
            }
        });
    }

    private void addwishlistRecord() {
        String wishType = selectedwish.getText().toString().trim();
        String type = selectwishfav.getSelectedItem().toString();
        String wishAmountText = wishamount.getText().toString().trim();

        if (wishType.isEmpty() || wishAmountText.isEmpty()) {
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        }

        if (!wishAmountText.isEmpty()) {
            double wishamount = Double.parseDouble(wishAmountText);

            // Add income record to the database
            boolean success = databaseHelper.addWish(wishType, type, wishamount, username);

            if (success) {
                Toast.makeText(this, "Wishlist record added successfully", Toast.LENGTH_SHORT).show();
                clearFields();
            } else {
                Toast.makeText(this, "Failed to add Wishlist record", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void clearFields() {

        selectedwish.getText().clear();
        wishamount.getText().clear();
    }

    private void setSingleEvent(GridLayout wishlayoutgrid) {
        for (int i = 0; i < wishlayoutgrid.getChildCount(); i++) {
            CardView card = (CardView) wishlayoutgrid.getChildAt(i);
            final int position = i;

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == 0) {
                        selectedwish.setText("Inventory");
                        Wishlistadp = ArrayAdapter.createFromResource(Wishlist.this, R.array.inventory, android.R.layout.simple_spinner_item);
                        Wishlistadp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        selectwishfav.setAdapter(Wishlistadp);

                    } else if (position == 1) {
                        selectedwish.setText("Grocery");
                        Wishlistadp = ArrayAdapter.createFromResource(Wishlist.this, R.array.grocery, android.R.layout.simple_spinner_item);
                        Wishlistadp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        selectwishfav.setAdapter(Wishlistadp);
                    } else if (position == 2) {
                        selectedwish.setText("Electronics");
                        Wishlistadp = ArrayAdapter.createFromResource(Wishlist.this, R.array.electronics, android.R.layout.simple_spinner_item);
                        Wishlistadp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        selectwishfav.setAdapter(Wishlistadp);
                    } else if (position == 3) {
                        selectedwish.setText("Gadgets");
                        Wishlistadp = ArrayAdapter.createFromResource(Wishlist.this, R.array.gadgets, android.R.layout.simple_spinner_item);
                        Wishlistadp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        selectwishfav.setAdapter(Wishlistadp);
                    } else if (position == 4) {
                        selectedwish.setText("Wearables");
                        Wishlistadp = ArrayAdapter.createFromResource(Wishlist.this, R.array.wearable, android.R.layout.simple_spinner_item);
                        Wishlistadp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        selectwishfav.setAdapter(Wishlistadp);
                    } else if (position == 5) {
                        selectedwish.setText("Furniture");
                        Wishlistadp = ArrayAdapter.createFromResource(Wishlist.this, R.array.furniture, android.R.layout.simple_spinner_item);
                        Wishlistadp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        selectwishfav.setAdapter(Wishlistadp);
                    }
                }
            });

        }
    }
}