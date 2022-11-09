package com.example.spinnerv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] elementos = {"León", "Zamora", "Salamanca", "Palencia", "Valladolid", "Ávila","Burgos", "Segovia", "Soria"};
        ArrayAdapter<String> adaptador;

        Spinner sp = (Spinner) findViewById(R.id.spinner);
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, elementos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adaptador);
        sp.setOnItemSelectedListener(this);


    }
    public void onItemSelected(AdapterView<?> a, View view, int position, long id){
        TextView t=(TextView)findViewById(R.id.textView);
        Spinner sp = (Spinner) findViewById(R.id.spinner);
        t.setText(sp.getSelectedItem().toString());
    }

    public void onNothingSelected(AdapterView<?> a){
        TextView t=(TextView)findViewById(R.id.textView);
        t.setText("No se ha seleccionado nada");
    }
}