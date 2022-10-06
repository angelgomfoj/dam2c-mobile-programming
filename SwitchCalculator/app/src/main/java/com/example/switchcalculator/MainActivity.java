package com.example.switchcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText num1, num2;
    private RadioGroup radioGroup;
    private RadioButton suma, resta, multiplicacion, division;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        suma = (RadioButton) findViewById(R.id.suma);
        resta = (RadioButton) findViewById(R.id.resta);
        multiplicacion = (RadioButton) findViewById(R.id.multiplicacion);
        division = (RadioButton) findViewById(R.id.division);
        result = (TextView) findViewById(R.id.result);

        radioGroup.setOnCheckedChangeListener(this::onCheckedChanged);

    }
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        int valor1 = Integer.parseInt(num1.getText().toString());
        int valor2 = Integer.parseInt(num2.getText().toString());
        switch (checkedId) {
            case R.id.suma:
                result.setText(String.valueOf(valor1 + valor2));
                break;
            case R.id.resta:
                result.setText(String.valueOf(valor1 - valor2));
                break;
            case R.id.multiplicacion:
                result.setText(String.valueOf(valor1 * valor2));
                break;
            case R.id.division:
                result.setText(String.valueOf(valor1 / valor2));
                break;
        }
    }
}