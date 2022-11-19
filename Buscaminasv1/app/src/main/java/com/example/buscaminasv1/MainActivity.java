package com.example.buscaminasv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnCuadradoClickListener{
    RecyclerView gridRecyclerView;
    AdaptadorTablero adaptador;
    Buscaminas buscaminas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridRecyclerView = findViewById(R.id.activity_main_grid);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 10));
        buscaminas = new Buscaminas(10);
        adaptador = new AdaptadorTablero(buscaminas.getTablero().getCuadrados(), this);
        gridRecyclerView.setAdapter(adaptador);
    }

    @Override
    public void OnCuadradoClick(Cuadrado cuadrado){
        Toast.makeText(getApplicationContext(), "Cuadrado clickado", Toast.LENGTH_SHORT).show();
    }
}