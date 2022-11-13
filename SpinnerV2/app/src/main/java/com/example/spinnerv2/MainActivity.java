package com.example.spinnerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    String[] animales = {"Gato", "Pollo", "Mono"};

    String[] textos = {"Es un felino", "Imagen de una gallina", "Un mono capaz de trepar arboles"};

    int imagenes[] = {R.drawable.cat, R.drawable.chicken, R.drawable.monkey};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner selectorPR = (Spinner) findViewById(R.id.spinner);
        AdaptadorPersonalizado a = new AdaptadorPersonalizado(this, R.layout.spinner_component, animales);
        selectorPR.setAdapter(a);
        selectorPR.setOnItemSelectedListener(this);
    }


    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner sp = (Spinner) findViewById(R.id.spinner);

        TextView titulo = (TextView) findViewById(R.id.mainTitle);
        ImageView peli = (ImageView) findViewById(R.id.mainImage);
        TextView description = (TextView) findViewById(R.id.mainText);

        titulo.setText(animales[sp.getSelectedItemPosition()]);
        peli.setImageResource(imagenes[sp.getSelectedItemPosition()]);
        description.setText(textos[sp.getSelectedItemPosition()]);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        Spinner sp = (Spinner) findViewById(R.id.spinner);

        TextView titulo = (TextView) findViewById(R.id.mainTitle);
        ImageView peli = (ImageView) findViewById(R.id.mainImage);
        TextView description = (TextView) findViewById(R.id.mainText);

        titulo.setText(imagenes[sp.getSelectedItemPosition()]);
        peli.setImageResource(imagenes[sp.getSelectedItemPosition()]);
        description.setText(textos[sp.getSelectedItemPosition()]);
    }


    public class AdaptadorPersonalizado extends ArrayAdapter<String> {

        public AdaptadorPersonalizado(Context ctx, int txtViewResourceId, String[] objects) {
            super(ctx, txtViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
            return filaSpinner(position, cnvtView, prnt);
        }

        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt) {
            return filaSpinner(pos, cnvtView, prnt);
        }
    }

    public View filaSpinner(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = getLayoutInflater();
        View filaSpinner = inflater.inflate(R.layout.spinner_component, parent, false);

        TextView title = (TextView) filaSpinner.findViewById(R.id.title);
        title.setText(animales[position]);

        TextView text = (TextView) filaSpinner.findViewById(R.id.text);
        text.setText(textos[position]);

        return filaSpinner;
    }

}
