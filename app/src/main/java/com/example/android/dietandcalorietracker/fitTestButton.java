
/**
 *Code Fixed By Harshvardhan Sahay
 * Earlier Code was Hot Garbage.... Really hot
*/
package com.example.android.dietandcalorietracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

public class fitTestButton extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    double height, weight, calorie, bmr;
    String protein, bmi, sex;
    int age, pos = -1;
    EditText weight_text;
    EditText height_text;
    EditText age_text;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_test_button);

        weight_text = findViewById(R.id.weight_text);
        height_text = findViewById(R.id.height_text);
        age_text = findViewById(R.id.age_text);
        spinner = findViewById(R.id.spinner_degree_of_activity);
        spinner.setOnItemSelectedListener(this);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.male_button:
                if (checked)
                    sex = "male";
                break;
            case R.id.female_button:
                if (checked)
                    sex = "female";
                break;
        }
    }

    public void take_test(View view) {


        DecimalFormat numberFormat = new DecimalFormat("#.00");

        try {

            weight = Double.parseDouble(weight_text.getText().toString());
            height = Double.parseDouble(height_text.getText().toString());
            age = Integer.parseInt(age_text.getText().toString());

            //Protein calculation
            protein = numberFormat.format(weight * 0.8);

            //BMI calculation
            bmi = numberFormat.format(weight / ((height / 100) * (height / 100)));

            //BMR calculations
            if (sex.equals("male"))
                bmr = ((10 * weight) + (6.25 * height) - (5 * age) + 5);
            else if (sex.equals("female"))
                bmr = ((10 * weight) + (6.25 * height) - (5 * age) - 161);

            if (pos == -1) {
//                        int falseException = 1 / 0;
                Exception e = new Exception();
                throw e;

            } else {
                switch (pos) {
                    case 0:
                        calorie = bmr * 1.2 + 2;
                        break;
                    case 1:
                        calorie = bmr * 1.375 + 2;
                        break;
                    case 2:
                        calorie = bmr * 1.55;
                        break;
                    case 3:
                        calorie = bmr * 1.725;
                        break;
                    case 4:
                        calorie = bmr * 1.9;
                        break;
                }
            }
            ApplicationSessionStorage.SetSessionData("BMI", bmi);

            ApplicationSessionStorage.SetSessionData("protein", protein + " gm");

            ApplicationSessionStorage.SetSessionData("bmr", bmr + "");

            ApplicationSessionStorage.SetSessionData("calorie", calorie + " cal");

            Intent fit_results = new Intent(fitTestButton.this, FitTestResult.class);
            startActivity(fit_results);


        } catch (Exception e) {

            Toast.makeText(this, "Please fill details", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object item = parent.getItemAtPosition(position);
        if (position != 0) {
            Toast.makeText(getApplicationContext(), "You selected " + item.toString(), Toast.LENGTH_SHORT).show();
            Log.i("posi", position + "");
        }

        switch (position - 1) {
            case 0:
                pos = 0;
                break;
            case 1:
                pos = 1;
                break;
            case 2:
                pos = 2;
                break;
            case 3:
                pos = 3;
                break;
            case 4:
                pos = 4;
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
