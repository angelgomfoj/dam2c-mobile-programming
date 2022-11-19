package com.example.buscaminasv1;

public class Cuadrado {
    public static final int BOMBA = -1;
    public static final int CUADRADO_VACIO = 0;

    private int valor;
    private boolean revelado;
    private boolean bandera;

    public Cuadrado(int valor) {
        this.valor = valor;
        this.revelado = false;
        this.bandera = false;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isRevelado() {
        return revelado;
    }

    public void setRevelado(boolean revelado) {
        this.revelado = revelado;
    }

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
}
