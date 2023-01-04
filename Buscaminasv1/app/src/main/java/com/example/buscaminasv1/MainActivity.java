package com.example.buscaminasv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnCuadradoClickListener{
    // Utilizamos RecyclerView, porque usa menos memoria y tiene un scroll mas eficiente, ademas de
    // forzarnos a usar el viewholder.
    // para mas info: https://www.geeksforgeeks.org/android-difference-between-recyclerview-and-listview/
    RecyclerView gridRecyclerView;
    AdaptadorTablero adaptador;
    Buscaminas buscaminas;

    // Creamos la App.
    // Aqui podemos modificar las dimensiones del tablero y el numero de bombas.
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
    // El metodo on Cuadrado click, que revela el cuadrado que clickemos y comprueba ademas si
    // con ese click el juego termina, y emite un mensaje en ese caso, con el resultado
    @Override
    public void onCuadradoClick(Cuadrado cuadrado) {
        buscaminas.handleCuadradoClick(cuadrado);

        if(buscaminas.isJuegoTerminado()){
            Toast.makeText(getApplicationContext(),"Derrota, el juego ha terminado.", Toast.LENGTH_SHORT).show();
            buscaminas.getTablero().revelarBombas();
            // Este bucle comentado es para comprobar el contenido de todas las casillas nada mas comenzar el juego
            // puede usarse aumentando el numero de bombas de la app, para ver los distintos colores
            // de los numeros de las casillas.

            /*for (Cuadrado c: buscaminas.getTablero().getCuadrados()) {
                c.setRevelado(true);
            }*/
        }else if(buscaminas.isJuegoGanado()){
            Toast.makeText(getApplicationContext(),"Victoria, has ganado!!!", Toast.LENGTH_SHORT).show();
            buscaminas.getTablero().revelarBombas();
        }
        adaptador.setCuadrados(buscaminas.getTablero().getCuadrados());
    }
    // El metodo on Cuadrado long click, pone una bandera en un cuadrado no revelado, o si
    // ya estaba puesta la elimina.
    @Override
    public void onCuadradoLongClick(Cuadrado cuadrado) {
        buscaminas.handleCuadradoLongClick(cuadrado);
        adaptador.setCuadrados(buscaminas.getTablero().getCuadrados());
    }

}