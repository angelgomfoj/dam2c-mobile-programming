package com.example.buscaminasv1;

import java.util.ArrayList;
import java.util.List;

public class Buscaminas {
    private Tablero tablero;
    private boolean juegoTerminado;

    public Buscaminas(int size, int numeroBombas){
        this.juegoTerminado = false;
        tablero = new Tablero(size);
        tablero.generateGrid(numeroBombas);
    }

    public void handleCuadradoClick(Cuadrado cuadrado){
        if(!juegoTerminado) {
                revelar(cuadrado);
        }
    }

    public void handleCuadradoLongClick(Cuadrado cuadrado) {
        if(!juegoTerminado){
            if(!cuadrado.isRevelado() && !cuadrado.isBandera()){
                cuadrado.setBandera(true);
            }else if(!cuadrado.isRevelado() && cuadrado.isBandera()){
                cuadrado.setBandera(false);
            }
        }
    }

    public void revelar(Cuadrado cuadrado){
        int index = getTablero().getCuadrados().indexOf(cuadrado);
        getTablero().getCuadrados().get(index).setRevelado(true);

        if(cuadrado.getValor() == Cuadrado.CUADRADO_VACIO){
            List<Cuadrado> cuadradosParaRevelar = new ArrayList<>();
            List<Cuadrado> cuadradosAdyacentesAComprobar = new ArrayList<>();

            cuadradosAdyacentesAComprobar.add(cuadrado);

            while (cuadradosAdyacentesAComprobar.size() > 0){
                Cuadrado c = cuadradosAdyacentesAComprobar.get(0);
                int cuadradoIndex = getTablero().getCuadrados().indexOf(c);
                int[] posicionCuadrado = getTablero().toXY(cuadradoIndex);
                for (Cuadrado adyacente: getTablero().cuadradosAdyacentes(posicionCuadrado[0],posicionCuadrado[1])){
                    if(adyacente.getValor() == Cuadrado.CUADRADO_VACIO){
                        if(!cuadradosParaRevelar.contains(adyacente)){
                            if(!cuadradosAdyacentesAComprobar.contains(adyacente)) {
                                cuadradosAdyacentesAComprobar.add(adyacente);
                            }
                        }
                    }else{
                        if(!cuadradosParaRevelar.contains(adyacente)){
                            cuadradosParaRevelar.add(adyacente);
                        }
                    }
                }
                cuadradosAdyacentesAComprobar.remove(c);
                cuadradosParaRevelar.add(c);
            }

            for (Cuadrado c: cuadradosParaRevelar) {
                c.setRevelado(true);
            }
        }else if(cuadrado.getValor() == Cuadrado.BOMBA){
            juegoTerminado = true;
        }
    }

    public boolean isJuegoGanado() {
        int numerosSinRevelar = 0;
        for (Cuadrado c: getTablero().getCuadrados()) {
            if(c.getValor() != Cuadrado.BOMBA && c.getValor() != Cuadrado.CUADRADO_VACIO && !c.isRevelado()) {
                numerosSinRevelar++;
            }
        }
        if(numerosSinRevelar > 0){
            return false;
        }else{
            return true;
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }


}
