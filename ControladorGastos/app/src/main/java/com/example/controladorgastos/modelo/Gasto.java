package com.example.controladorgastos.modelo;

import java.util.Date;

public class Gasto {
    private int id;
    private String categoria, descripcion;
    private double importe;
    private Date fecha;

    public Gasto() {
    }

    public Gasto(int id, String categoria, String descripcion, double importe, Date fecha) {
        this.id = id;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.importe = importe;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


}
