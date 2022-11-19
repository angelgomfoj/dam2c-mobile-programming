package com.example.buscaminasv1;

public class Buscaminas {
    private Tablero tablero;

    public Buscaminas(int size){
        tablero = new Tablero(size);
    }

    public Tablero getTablero() {
        return tablero;
    }
}
