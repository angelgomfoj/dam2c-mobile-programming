package com.example.menuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int iden = item.getItemId();
        switch (iden) {
            case R.id.Clientes:
                Toast.makeText(getApplicationContext(), "Pulsado Cliente",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.Facturas:
                Toast.makeText(getApplicationContext(), "Pulsado Facturas",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.ajustes:
                Toast.makeText(getApplicationContext(), "Pulsado Ajustes",
                        Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}