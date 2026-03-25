package com.example.travelcompanionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        editTextValue = findViewById(R.id.editTextValue);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        String[] categories = {"Currency","Fuel","Temperature"};
        String[] currency = {"USD", "AUD", "EUR", "JPY", "GBP"};
        String[] temperatureUnits = {"Celsius", "Fahrenheit", "Kelvin"};
        String[] fuelUnits = {"mpg", "km/L", "gallon", "liter", "nautical mile", "kilometer"};
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,categories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        ArrayAdapter<String> temperatureAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,temperatureUnits);
        temperatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,fuelUnits);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> fuelAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,fuelUnits);
        fuelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerCategory.setAdapter(categoriesAdapter);
        spinnerCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = spinnerCategory.getSelectedItem().toString();
                if (selectedCategory.equals("Currency")) {
                    spinnerFrom.setAdapter(currencyAdapter);
                    spinnerTo.setAdapter(currencyAdapter);
                } else if (selectedCategory.equals("Temperature")) {
                    spinnerFrom.setAdapter(temperatureAdapter);
                    spinnerTo.setAdapter(temperatureAdapter);
                } else if (selectedCategory.equals("Fuel")) {
                    spinnerFrom.setAdapter(fuelAdapter);
                    spinnerTo.setAdapter(fuelAdapter);
                }

            }

        });
        spinnerFrom.setAdapter(fuelAdapter);
        spinnerTo.setAdapter(fuelAdapter);

        //给按钮加事件
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputValue = editTextValue.getText().toString();
                String category = spinnerCategory.getSelectedItem().toString();
                String fromUnit = spinnerFrom.getSelectedItem().toString();
                String toUnit = spinnerTo.getSelectedItem().toString();

                double value = Double.parseDouble(inputValue);
                if (inputValue.isEmpty()){
                    editTextValue.setError("Please enter a value");
                    return;
                }

                if (category.equals("Currency")) {
                    double result = convertCurrency(fromUnit, toUnit, value);
                    textViewResult.setText(value + " " + fromUnit + " = " + result + " " + toUnit);
                } else if (category.equals("Temperature")){
                    double result = convertTemperature(fromUnit, toUnit, value);
                    textViewResult.setText(value + " " + fromUnit + " = " + result + " " + toUnit);

                }


            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private Spinner spinnerCategory;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private EditText editTextValue;
    private Button buttonConvert;
    private TextView textViewResult;

    private double convertCurrency(String from,String to, double value) {
        if (from.equals(to)){
            return value;
        }
        double usd;
        switch (from) {
            case "USD":
                usd = value;
                break;
            case "AUD":
                usd = value / 1.55;
                break;
            case "EUR":
                usd = value / 0.92;
                break;
            case "JPY":
                usd = value / 148.50;
                break;
            case "GBP":
                usd = value / 0.78;
                break;
            default:
                return 0;
        }

        switch (to) {
            case "USD":
                return usd;
            case "AUD":
                return usd * 1.55;
            case "EUR":
                return usd * 0.92;
            case "JPY":
                return usd * 148.50;
            case "GBP":
                return usd * 0.78;
            default:
                return 0;
        }
    }
    private double convertTemperature(String from, String to, double value){
        double C ;
//直接都转成摄氏度 然后下面就可以当做摄氏度来case
        switch (from) {
            case "Celsius":
                C = value;
                break;
            case "Fahrenheit":
                C = (value - 32) / 1.8;
                break;
            case "Kelvin":
                C = value - 273.15;
                break;
            default:
                return 0;
        }
        switch (to) {
            case "Celsius":
                return C;
            case "Fahrenheit":
                return (C * 1.8) + 32;
            case "Kelvin":
                return C + 273.15;
            default:
                return 0;
        }
    }
    private double convertFuel(String from, String to, double value){
        double C ;

        switch (from) {
            case "Celsius":
                C = value;
                break;
            case "Fahrenheit":
                C = (value - 32) / 1.8;
                break;
            case "Kelvin":
                C = value - 273.15;
                break;
            default:
                return 0;
        }
        switch (to) {
            case "Celsius":
                return C;
            case "Fahrenheit":
                return (C * 1.8) + 32;
            case "Kelvin":
                return C + 273.15;
            default:
                return 0;
        }
    }

    }
