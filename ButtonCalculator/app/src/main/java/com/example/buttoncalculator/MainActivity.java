package com.example.buttoncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, sumar, restar, dividir,
            multiplicar, button10, borrar, igual;
    EditText pantalla;
    float valor1, valor2;
    boolean bSuma, bResta, bMultiplicacion, bDivision;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        sumar = (Button) findViewById(R.id.sumar);
        restar = (Button) findViewById(R.id.restar);
        multiplicar = (Button) findViewById(R.id.multiplicar);
        dividir = (Button) findViewById(R.id.dividir);
        borrar = (Button) findViewById(R.id.borrar);
        igual = (Button) findViewById(R.id.igual);
        pantalla = (EditText) findViewById(R.id.pantalla);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText(pantalla.getText() + "1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText(pantalla.getText() + "2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText(pantalla.getText() + "3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText(pantalla.getText() + "4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText(pantalla.getText() + "5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText(pantalla.getText() + "6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText(pantalla.getText() + "7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText(pantalla.getText() + "8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText(pantalla.getText() + "9");
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText(pantalla.getText() + "0");
            }
        });
        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pantalla == null) {
                    pantalla.setText("");
                } else {
                    valor1 = Float.parseFloat(pantalla.getText() + "");
                    bSuma = true;
                    pantalla.setText(null);
                }
            }
        });
        restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor1 = Float.parseFloat(pantalla.getText() + "");
                bResta = true;
                pantalla.setText(null);
            }
        });
        multiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor1 = Float.parseFloat(pantalla.getText() + "");
                bMultiplicacion = true;
                pantalla.setText(null);
            }
        });
        dividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor1 = Float.parseFloat(pantalla.getText() + "");
                bDivision = true;
                pantalla.setText(null);
            }
        });
        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor2 = Float.parseFloat(pantalla.getText() + "");
                if (bSuma == true) {
                    pantalla.setText(valor1 + valor2 + "");
                    bSuma = false;
                }
                if (bResta == true) {
                    pantalla.setText(valor1 - valor2 + "");
                    bResta = false;
                }
                if (bMultiplicacion == true) {
                    pantalla.setText(valor1 * valor2 + "");
                    bMultiplicacion = false;
                }
                if (bDivision == true) {
                    pantalla.setText(valor1 / valor2 + "");
                    bDivision = false;
                }
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText("");
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText(pantalla.getText() + ".");
            }
        });

    }
}