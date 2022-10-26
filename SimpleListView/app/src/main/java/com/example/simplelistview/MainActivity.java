package com.example.simplelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

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
    public void onItemClick(AdapterView<?> a, View view, int position, long id){
        TextView t=(TextView)findViewById(R.id.textView);
        t.setText("Has elegido:"+a.getItemAtPosition(position).toString());
//t.setText("Has elegido:"+((TextView)view).getText()); //OTRA OPCIÓN
    }

}