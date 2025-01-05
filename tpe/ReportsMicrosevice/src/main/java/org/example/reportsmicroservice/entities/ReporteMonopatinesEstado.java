package org.example.reportsmicroservice.entities;

public class ReporteMonopatinesEstado{
    private String nombre;
    private String descripcion;
    private Integer cantActivos;
    private Integer cantMantenimiento;

    public ReporteMonopatinesEstado(Integer a, Integer m){
        this.nombre = "Reporte de monopatines según estado";
        this.descripcion = "El reporte provee una comparacion de la cantidad de monopatines activos versus la cantidad en mantenimiento";
        this.cantActivos = a;
        this.cantMantenimiento = m;
    }

    @Override
    public String toString() {
        return "Nombre del reporte: " + this.nombre + "Descripcion: " + this.descripcion + "Cantidad de monopatines activos: " + this.cantActivos +"Cantidad de monopatines en mantenimiento: " + this.cantMantenimiento;
    }
}
