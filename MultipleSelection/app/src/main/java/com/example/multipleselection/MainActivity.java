package com.example.multipleselection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
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
        adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,elementos);
        l.setAdapter(adaptador);
        l.setOnItemClickListener(this);




    }
    public void onItemClick(AdapterView<?> a, View view, int position, long
            id){
        TextView t=(TextView)findViewById(R.id.textView);
        ListView l=(ListView)findViewById(R.id.listView);
        StringBuffer seleccionado= new StringBuffer("Has elegido: ");
        SparseBooleanArray checked = l.getCheckedItemPositions();
        for(int i=0;i<checked.size();i++)
            if(checked.valueAt(i)){
                seleccionado.append(a.getItemAtPosition(checked.keyAt(i)).toString());
                seleccionado.append(";");
            }
        t.setText(seleccionado.toString());
    }


}