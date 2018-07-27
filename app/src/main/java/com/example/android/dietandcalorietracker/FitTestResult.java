package com.example.android.dietandcalorietracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FitTestResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_test_result);

        //Displaying calorie
        TextView cal_count = findViewById(R.id.calorie_count);
        cal_count.setText(ApplicationSessionStorage.GetSessionData("calorie"));

        //Displaying BMI
        TextView bmi = findViewById(R.id.bmi_count);
        bmi.setText(ApplicationSessionStorage.GetSessionData("BMI"));

        //Displaying Protein intake
        TextView protein_count = findViewById(R.id.protein_count);
        protein_count.setText(ApplicationSessionStorage.GetSessionData("protein"));

        //Displaying bmr
        TextView bmr_count = findViewById(R.id.bmr_count);
        bmr_count.setText(ApplicationSessionStorage.GetSessionData("bmr"));


        ImageView help = findViewById(R.id.help_img);
        help.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors View is clicked on.
            @Override
            public void onClick(View view) {
                Intent help = new Intent(FitTestResult.this, help_txt.class);
                startActivity(help);
            }
        });


    }



}
