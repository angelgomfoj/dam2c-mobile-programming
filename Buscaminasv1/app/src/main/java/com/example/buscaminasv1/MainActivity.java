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
        buscaminas = new Buscaminas(10,10);
        adaptador = new AdaptadorTablero(buscaminas.getTablero().getCuadrados(), this);
        gridRecyclerView.setAdapter(adaptador);
    }

    @Override
    public void onCuadradoClick(Cuadrado cuadrado) {
        buscaminas.handleCuadradoClick(cuadrado);

        if(buscaminas.isJuegoTerminado()){
            Toast.makeText(getApplicationContext(),"El juego ha terminado.", Toast.LENGTH_SHORT).show();
            buscaminas.getTablero().revelarBombas();
            /*for (Cuadrado c: buscaminas.getTablero().getCuadrados()) {
                c.setRevelado(true);
            }*/
        }else if(buscaminas.isJuegoGanado()){
            Toast.makeText(getApplicationContext(),"Enhorabuena, has ganado!!!.", Toast.LENGTH_SHORT).show();
            buscaminas.getTablero().revelarBombas();
        }
        adaptador.setCuadrados(buscaminas.getTablero().getCuadrados());
    }
}