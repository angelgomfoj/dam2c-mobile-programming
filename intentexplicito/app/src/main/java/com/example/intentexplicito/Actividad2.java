package com.example.intentexplicito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Actividad2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        Bundle extras = getIntent().getExtras();
        String s1 = extras.getString("v1");
        String s2 = extras.getString("v2");
        Toast.makeText(getApplicationContext(),s1+
                "\n"+s2, Toast.LENGTH_LONG).show();

    }

    public void callFirstActivity(View view){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}