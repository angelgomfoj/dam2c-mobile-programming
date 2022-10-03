package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText num1, num2;
    Button button;
    RadioGroup radioGroup;
    RadioButton suma, resta, multiplicacion, division;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        button = (Button) findViewById(R.id.button);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        suma = (RadioButton) findViewById(R.id.suma);
        resta = (RadioButton) findViewById(R.id.resta);
        multiplicacion = (RadioButton) findViewById(R.id.multiplicacion);
        division = (RadioButton) findViewById(R.id.division);
        result = (TextView) findViewById(R.id.result);
    }
    public void onClick(View view) {
        int valor1 = Integer.parseInt(num1.getText().toString());
        int valor2 = Integer.parseInt(num2.getText().toString());
        int resultadox;
        if(suma.isSelected()){
            resultadox = valor1+valor2;
        }else if(resta.isSelected()){
            resultadox = valor1 - valor2;
        }else if(multiplicacion.isSelected()){
            resultadox = valor1 * valor2;
        }else if(division.isSelected()){
            resultadox = valor1 / valor2;
        }else{
            resultadox = Integer.MIN_VALUE;
        }
        result.setText(resultadox);
    }
}