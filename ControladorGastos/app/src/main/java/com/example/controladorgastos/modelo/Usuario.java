package com.example.controladorgastos.modelo;

public class Usuario {

    private String username;
    private double limiteGastos;

    public Usuario() {
    }

    public Usuario(String username, double limiteGastos) {
        this.username = username;
        this.limiteGastos = limiteGastos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getLimiteGastos() {
        return limiteGastos;
    }

    public void setLimiteGastos(double limiteGastos) {
        this.limiteGastos = limiteGastos;
    }
}
