package com.example.intentexplicito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callSecondActivity(View view){
        Intent i = new Intent(getApplicationContext(), Actividad2.class);
        i.putExtra("v1", "Valor recibido por la primera actividad:");
        i.putExtra("v2", "Intent explicito Angel Gomez");
        startActivity(i);
    }
}