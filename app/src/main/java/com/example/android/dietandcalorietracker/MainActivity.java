package com.example.android.dietandcalorietracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Intent on Search button button
        Button search_food = findViewById(R.id.search_view);
        search_food.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent(MainActivity.this, Search_food.class);
                startActivity(colorsIntent);
            }
        });


        //Intent on fit test button
        Button fitTestButton = findViewById(R.id.fitTestButton);
        fitTestButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent(MainActivity.this, fitTestButton.class);
                startActivity(colorsIntent);
            }
        });


// About Us text Intent
        TextView about_text = findViewById(R.id.about_us);
        about_text.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent aboutIntent = new Intent(MainActivity.this, about_us.class);
                startActivity(aboutIntent);
            }
        });

    }


}
