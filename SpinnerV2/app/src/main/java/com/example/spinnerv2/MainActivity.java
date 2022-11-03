package com.example.spinnerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    String[] ciudades = {"Toledo", "Ciudad Real", "Albacete", "Cuenca",
            "Guadalajara"};
    String[] descripciones = {"La ciudad Imperial", "Qué gran ciudad",
            "Ciudad gastronómica", "Ciudad encantada", "Ciudad colgante"};
    // int imagenes[] = { R.drawable.toledo, R.drawable.ciudadreal,
    //       R.drawable.albacete, R.drawable.cuenca, R.drawable.guadalajara};

    public class AdaptadorPersonalizado extends ArrayAdapter<String> {
        public AdaptadorPersonalizado(Context ctx, int txtViewResourceId, String[]
                objects) {
            super(ctx, txtViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
            return crearFilaPersonalizada(position, cnvtView, prnt);
        }

        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt) {
            return crearFilaPersonalizada(pos, cnvtView, prnt);
        }

        public View crearFilaPersonalizada(int position, View convertView,
                                           ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View miFila = inflater.inflate(R.layout.lineaspiner, parent,
                    false);
            TextView nombre = (TextView) miFila.findViewById(R.id.nombre);
            nombre.setText(ciudades[position]);
            TextView descripcion = (TextView)
                    miFila.findViewById(R.id.descripcion);
            descripcion.setText(descripciones[position]);
            ImageView imagen = (ImageView)
                    miFila.findViewById(R.id.imagenCiudad);
            imagen.setImageResource(imagenes[position]);
            return miFila;

            @Override
            protected void onCreate (Bundle savedInstanceState){
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Spinner selectorCiudades = (Spinner) findViewById(R.id.spinner);
                AdaptadorPersonalizado a = new AdaptadorPersonalizado(this,
                        R.layout.lineaspiner, ciudades);
                selectorCiudades.setAdapter(a);
                selectorCiudades.setOnItemSelectedListener(this);
            }
        }
    }
}