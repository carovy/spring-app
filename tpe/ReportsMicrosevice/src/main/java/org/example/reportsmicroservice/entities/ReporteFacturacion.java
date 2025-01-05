package org.example.reportsmicroservice.entities;

import java.time.LocalDate;
import java.util.Date;


public class ReporteFacturacion{
    private String nombre;
    private String descripcion;
    private double totalFacturado;
    private String fechaInicio;
    private String fechaFin;

    public ReporteFacturacion(String nombre, String descripcion, double totalFacturado, String fechaInicio, String fechaFin) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.totalFacturado = totalFacturado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Nombre del reporte: " + this.nombre + " Descripcion: " + this.descripcion +
                " Total facturado: " + this.totalFacturado + " Desde: " + this.fechaInicio + " Hasta: " + this.fechaFin;
    }

    public double getTotalFacturado() {
        return totalFacturado;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }
}
