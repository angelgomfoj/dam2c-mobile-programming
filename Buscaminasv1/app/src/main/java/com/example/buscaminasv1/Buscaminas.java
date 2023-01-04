package com.example.buscaminasv1;

import java.util.ArrayList;
import java.util.List;

public class Buscaminas {
    private Tablero tablero;
    private boolean juegoTerminado;

    // Constructor que determina el size del tablero y el numero de bombas que va a haber
    public Buscaminas(int size, int numeroBombas){
        this.juegoTerminado = false;
        tablero = new Tablero(size);
        tablero.generateGrid(numeroBombas);
    }
    // Si el juego no ha terminado, al hacer click sobre un cuadrado revela su contenido.
    public void handleCuadradoClick(Cuadrado cuadrado){
        if(!juegoTerminado) {
                revelar(cuadrado);
        }
    }
    // Si el juego no ha terminado, al hacer un long click en un cuadrado no revelado
    // pone una bandera en ese cuadrado.
    // Si ya hubiera una bandera, hace el efecto contrario, la suprime.
    public void handleCuadradoLongClick(Cuadrado cuadrado) {
        if(!juegoTerminado){
            if(!cuadrado.isRevelado() && !cuadrado.isBandera()){
                cuadrado.setBandera(true);
            }else if(!cuadrado.isRevelado() && cuadrado.isBandera()){
                cuadrado.setBandera(false);
            }
        }
    }
    // El metodo revelar, revela el contenido del cuadrado que recibe por parametro,
    // si este contenido es una bomba, el juego termina, en caso contrario, revela el valor de ese
    // cuadrado y ademas el de los adyacentes si son cuadrados vacios.
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
    // Comprueba si el tablero ha sido revelado entero excepto las bombas y retorna true en ese caso
    // o false en caso contrario
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
    // Este metodo devuelve el tablero
    public Tablero getTablero() {
        return tablero;
    }
    // Este metodo devuelve si el juego ha terminado.
    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }


}
