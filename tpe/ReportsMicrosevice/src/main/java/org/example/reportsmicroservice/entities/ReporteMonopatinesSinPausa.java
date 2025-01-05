package org.example.reportsmicroservice.entities;

import lombok.Data;

@Data
public class ReporteMonopatinesSinPausa {
    private String nombre;
    private String descripcion;
    private Long idMonop;
    private float maxKm;
    private float tiempo;

    public ReporteMonopatinesSinPausa(Long id, float km, float t){
        this.nombre = "Reporte de uso de monopatines";
        this.descripcion = "El reporte provee una descripcion sobre el uso de monopatines en relacion los kilometros recorridos y el tiempo de uso (con o sin pausa)";
        this.idMonop = id;
        this.maxKm = km;
        this.tiempo = t;
    }

@Override
    public String toString(){
        return "Nombre del reporte: " + this.nombre + "Descripcion: " + this.descripcion + " Id del monopatin: " + this.idMonop + " Cantidad de km recorridos: " + this.maxKm + " Tiempo max de uso: " + this.tiempo;
    }
}
