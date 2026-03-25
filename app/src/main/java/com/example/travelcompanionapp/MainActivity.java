package com.example.travelcompanionapp;

import android.os.Bundle;
import android.view.View;
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
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,categories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        String[] currency = {"USD", "AUD", "EUR", "JPY", "GBP"};
        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,currency);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategory.setAdapter(categoriesAdapter);
        spinnerFrom.setAdapter(currencyAdapter);
        spinnerTo.setAdapter(currencyAdapter);
        //给按钮加事件
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputValue = editTextValue.getText().toString();
                String category = spinnerCategory.getSelectedItem().toString();
                String fromUnit = spinnerFrom.getSelectedItem().toString();
                String toUnit = spinnerTo.getSelectedItem().toString();

                if (inputValue.isEmpty()){
                    editTextValue.setError("Please enter a value");
                    return;
                }
                textViewResult.setText(
                        "Category:" + category + "value" + "From" +fromUnit + "to"+ toUnit+"is"
                );
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

}