package com.example.datepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements
        MyDialog.OnFechaSeleccionada {
    EditText date,time;

/*
    @Override
    public void onResultadoFecha(String s) {
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG
        ).show();
    }
    */
    public void click(View v){
        MyDialog ds=new MyDialog();
        ds.show(getFragmentManager(),"Mi diálogo");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = (EditText) findViewById(R.id.editTextDate);
        time = (EditText) findViewById(R.id.editTextTime);
    }

    @Override
    public void onResultadoFecha(GregorianCalendar g) {
    date.setText(g.get());
    }
}