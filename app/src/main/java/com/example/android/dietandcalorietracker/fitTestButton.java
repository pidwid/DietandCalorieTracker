/*package com.example.android.dietandcalorietracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.NumberFormat;



public class fitTestButton extends AppCompatActivity {


    //Global
    double weight;
    double height;
    int age;
    boolean male;
    boolean female;

    double total_calorie = 0.0;
    double total_protein = 0.0;
    double total_bmi = 0.0;
    double total_bmr = 0.0;
    boolean checked;


    public void onRadioButtonClicked(View v) {
        // Is the current Radio Button checked?
        checked = ((RadioButton) v).isChecked();


        switch (v.getId()) {

            case R.id.male_button:
                if (checked)
                    total_bmr = ((10 * weight) + (6.25 * height) - (5 * age) + 5);
                break;

            case R.id.female_button:
                if (checked)
                    total_bmr = ((10 * weight) + (6.25 * height) - (5 * age) - 161);
                break;
        }


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_test_button);



        //Function for limiting decimal to two digits.
        final NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);





            //Spinner
            final Spinner spinner = findViewById(R.id.spinner_degree_of_activity);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.Activity, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
            spinner.setAdapter(adapter);

        class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                Object item = parent.getItemAtPosition(pos);

                Toast.makeText(this, "You selected " + item.toString(), Toast.LENGTH_SHORT).show();
                Log.i("posi",pos+ "");

                switch(pos) {
                    case 0:
                        total_calorie = total_bmr * 1.2+2;
                        break;
                    case 1:
                        total_calorie =total_bmr * 1.375+2;
                        break;
                    case 2:
                     total_calorie = total_bmr * 1.55;
                        break;
                    case 3:
                        total_calorie =total_bmr * 1.725;
                        break;
                    case 4:
                        total_calorie = total_bmr * 1.9;
                        break;
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        }




        try {

            //Intent For Fit test results
            Button FitTestResult = findViewById(R.id.take_test);
            FitTestResult.setOnClickListener(new View.OnClickListener() {


                // The code in this method will be executed when the Fit button is clicked on.
                @Override
                public void onClick(View view) {


                    Intent fit_results = new Intent(fitTestButton.this, FitTestResult.class);
                    startActivity(fit_results);

                    //Accepting weight
                    EditText weight_text = findViewById(R.id.weight_text);
                    weight = Double.parseDouble(weight_text.getText().toString());

                    //Accepting height
                    EditText height_text = findViewById(R.id.height_text);
                    height = Double.parseDouble(height_text.getText().toString());

                    //Accepting Age
                    EditText age_text = findViewById(R.id.age_text);
                    age = Integer.parseInt(age_text.getText().toString());


                    //BMI calculation
                    total_bmi = weight / ((height / 100) * (height / 100));
                    String bmi_format = formatter.format(total_bmi);
                    ApplicationSessionStorage.SetSessionData("BMI", bmi_format);

                    //Protein calculation
                    total_protein = weight * 0.8;
                    String protein_format = formatter.format(total_protein);
                    ApplicationSessionStorage.SetSessionData("protein", protein_format + " gm");

                    //bmr calculation
                    ApplicationSessionStorage.SetSessionData("bmr", total_bmr + "");

                    //calorie calculation
                    ApplicationSessionStorage.SetSessionData("calorie", total_calorie + " cal");


                }


            });

        } catch (Exception e) {

            Toast.makeText(this, "Please fill details", Toast.LENGTH_SHORT).show();
        }


    }
}*/
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

public class fitTestButton extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    double height, weight, calorie, bmr; String protein, bmi, sex;
    int age, pos;
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
        age_text    = findViewById(R.id.age_text);
        spinner     = findViewById(R.id.spinner_degree_of_activity);
        spinner.setOnItemSelectedListener(this);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
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
        weight = Double.parseDouble(weight_text.getText().toString());
        height = Double.parseDouble(height_text.getText().toString());
        age    = Integer.parseInt(age_text.getText().toString());

        DecimalFormat numberFormat = new DecimalFormat("#.00");


        //Protein calculation
        protein = numberFormat.format(weight * 0.8);

        //BMI calculation
        bmi = numberFormat.format(weight / ((height / 100) * (height / 100)));

        //BMR calculations
        if     (sex.equals("male"))
            bmr = ((10 * weight) + (6.25 * height) - (5 * age) + 5);
        else if(sex.equals("female"))
            bmr = ((10 * weight) + (6.25 * height) - (5 * age) - 161);

        switch(pos) {
            case 0:
                calorie = bmr * 1.2+2;
                break;
            case 1:
                calorie = bmr * 1.375+2;
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

        ApplicationSessionStorage.SetSessionData("BMI", bmi);

        ApplicationSessionStorage.SetSessionData("protein", protein + " gm");

        ApplicationSessionStorage.SetSessionData("bmr", bmr + "");

        ApplicationSessionStorage.SetSessionData("calorie", calorie + " cal");

        Intent fit_results = new Intent(fitTestButton.this, FitTestResult.class);
        startActivity(fit_results);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object item = parent.getItemAtPosition(position + 1);
        if(position != 0) {
            Toast.makeText(getApplicationContext(), "You selected " + item.toString(), Toast.LENGTH_SHORT).show();
            Log.i("posi", position + "");
        }

        switch(position - 1) {
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