package com.shahab12344.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    String username;

    GridLayout gridLayout;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.Logout:
                Intent logout = new Intent(getApplicationContext(),all_wishlist.class);
                logout.putExtra("user",username);
                startActivity(logout);
                break;


            case R.id.Wish:
                Intent wish = new Intent(getApplicationContext(),Login.class);
                startActivity(wish);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridLayout = findViewById(R.id.gridlayout);
        setSingleEvent(gridLayout);


        //   datretirve
         username = getIntent().getStringExtra("userlog");




    }

    private void setSingleEvent(GridLayout gridLayout) {
        for(int i = 0; i < gridLayout.getChildCount(); i++){
            CardView card = (CardView) gridLayout.getChildAt(i);
            final int position = i;

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(position == 0){
                        Intent income = new Intent(MainActivity.this, Income.class);
                        income.putExtra("user",username);
                        startActivity(income);
                    }
                  else  if(position == 1){
                        Intent expense = new Intent(MainActivity.this, Expense.class);
                        expense.putExtra("user",username);
                        startActivity(expense);
                    }

                    else if(position == 2){
                        Intent saving = new Intent(MainActivity.this, Wishlist.class);
                        saving.putExtra("user",username);
                        startActivity(saving);
                    }

                    else if(position == 3){
                        Intent debit = new Intent(MainActivity.this, Debit.class);
                        debit.putExtra("user",username);
                        startActivity(debit);
                    }
                    else if(position == 4){
                        Intent save = new Intent(MainActivity.this, Saving.class);
                        save.putExtra("user",username);
                        startActivity(save);
                    }
                    else if(position == 5){
                        Intent history = new Intent(MainActivity.this, PendingDebits.class);
                        history.putExtra("user",username);
                        startActivity(history);
                    }
                    else if(position == 6){
                        Intent alldebit = new Intent(MainActivity.this, all_debits.class);
                        alldebit.putExtra("user",username);
                        startActivity(alldebit);
                    }
                    else if(position == 7){
                        Intent allexpense = new Intent(MainActivity.this, all_expenses.class);
                        allexpense.putExtra("user",username);
                        startActivity(allexpense);
                    }
                }
            });

        }
    }
}