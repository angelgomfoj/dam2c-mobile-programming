package com.example.datepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements
        DateDialog.OnFechaSeleccionada, TimeDialog.OnHoraSeleccionada {
    EditText date,time;

    public void click(View v){
        DateDialog ds=new DateDialog();
        ds.show(getFragmentManager(),"Mi diálogo");

    }
    public void click2(View v){
        TimeDialog ds=new TimeDialog();
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
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        fmt.setCalendar(g);
        String dateFormatted = fmt.format(g.getTime());

        date.setText(dateFormatted);

    }

    @Override
    public void onResultadoHora(GregorianCalendar g) {
        SimpleDateFormat fmt = new SimpleDateFormat("hh:mm");
        fmt.setCalendar(g);
        String dateFormatted = fmt.format(g.getTime());

        time.setText(dateFormatted);

    }
}