package com.example.intent1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Activity2 extends AppCompatActivity implements ListView.OnItemClickListener{

    public void onItemClick(AdapterView<?> a, View view, int position, long id){
        Intent i=new Intent();
        i.putExtra("PROVINCIA",a.getItemAtPosition(position).toString());
        setResult(RESULT_OK,i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }
}