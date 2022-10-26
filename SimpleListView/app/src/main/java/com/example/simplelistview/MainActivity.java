package com.example.simplelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] elementos={"León","Zamora","Salamanca","Palencia","Valladolid","Ávila","Burgos",
                "Segovia","Soria"};
        ArrayAdapter<String> adaptador;
        ListView l=(ListView)findViewById(R.id.listView);
        adaptador=new ArrayAdapter<String>(this,R.layout.fila,elementos);
        l.setAdapter(adaptador);
        l.setOnItemClickListener(this);
    }
}