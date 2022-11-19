package com.example.buscaminasv1;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private List<Cuadrado> cuadrados;
    private int size;

    public Tablero(int size){
        this.size = size;
        cuadrados = new ArrayList<>();
        for(int i = 0; i < size*size; i++){
            cuadrados.add(new Cuadrado(Cuadrado.CUADRADO_VACIO));
        }
    }

    public List<Cuadrado> getCuadrados() {
        return cuadrados;
    }
}
