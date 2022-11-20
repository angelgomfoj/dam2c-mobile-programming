package com.example.buscaminasv1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void generateGrid(int numeroBombas){
        int bombasColocadas = 0;
        while(bombasColocadas < numeroBombas){
            int x = new Random().nextInt(size);
            int y = new Random().nextInt(size);

            int index = toIndex(x,y);
            if(cuadrados.get(index).getValor() == Cuadrado.CUADRADO_VACIO){
                cuadrados.set(index, new Cuadrado(Cuadrado.BOMBA));
                bombasColocadas++;
            }
        }
        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                if(cuadradoEn(x,y).getValor() != Cuadrado.BOMBA) {
                    List<Cuadrado> cuadradosAdyacentes = cuadradosAdyacentes(x,y);
                    int numBombas = 0;
                    for (Cuadrado c: cuadradosAdyacentes) {
                        if(c.getValor() == Cuadrado.BOMBA){
                            numBombas++;
                        }
                    }
                    if(numBombas > 0){
                        cuadrados.set(x+(y*size),new Cuadrado(numBombas));
                    }
                }
            }
        }
    }

    public List<Cuadrado> cuadradosAdyacentes(int x, int y){
        List<Cuadrado> cuadradosAdyacentes = new ArrayList<>();

        List<Cuadrado> listaCuadrados = new ArrayList<>();

        listaCuadrados.add(cuadradoEn(x-1,y));
        listaCuadrados.add(cuadradoEn(x+1,y));
        listaCuadrados.add(cuadradoEn(x-1,y-1));
        listaCuadrados.add(cuadradoEn(x,y-1));
        listaCuadrados.add(cuadradoEn(x+1,y-1));
        listaCuadrados.add(cuadradoEn(x-1,y+1));
        listaCuadrados.add(cuadradoEn(x,y+1));
        listaCuadrados.add(cuadradoEn(x+1,y+1));

        for (Cuadrado c: listaCuadrados) {
            if(c != null){
                cuadradosAdyacentes.add(c);
            }
        }

        return cuadradosAdyacentes;
    }

    public void revelarBombas() {
        for (Cuadrado c: cuadrados) {
            if(c.getValor() == Cuadrado.BOMBA){
                c.setRevelado(true);
            }
        }
    }

    public int toIndex(int x, int y){
        return x + (y*size);
    }

    public int[] toXY(int index){
        int y = index/size;
        int x = index - (y*size);
        return new int[]{x,y};
    }

    public Cuadrado cuadradoEn(int x, int y){
        if(x < 0 || x >= size || y < 0 || y >= size) {
            return null;
        }
        return cuadrados.get(toIndex(x,y));
    }

    public List<Cuadrado> getCuadrados() {
        return cuadrados;
    }
}
