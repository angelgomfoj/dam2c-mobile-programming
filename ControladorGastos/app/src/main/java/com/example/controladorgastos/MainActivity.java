package com.example.controladorgastos;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.controladorgastos.DatabaseHandler;

import com.example.controladorgastos.fragments.AddGastos;
import com.example.controladorgastos.fragments.ViewGastos;
import com.example.controladorgastos.fragments.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());


        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        settingsFragment = new Settings(bottomNavigationView);

        if(db.getUsuario()==null){
            bottomNavigationView.setSelectedItemId(R.id.settings);
            bottomNavigationView.getMenu().getItem(1).setEnabled(false);
            bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        }else{
            bottomNavigationView.setSelectedItemId(R.id.addGastos);
        }
    }
    AddGastos addGastosFragment = new AddGastos();
    ViewGastos viewGastosFragment = new ViewGastos();
    Settings settingsFragment = new Settings(bottomNavigationView);

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addGastos:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, addGastosFragment).commit();
                return true;

            case R.id.viewGastos:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, viewGastosFragment).commit();
                return true;

            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, settingsFragment).commit();
                return true;
        }
        return false;
    }

}