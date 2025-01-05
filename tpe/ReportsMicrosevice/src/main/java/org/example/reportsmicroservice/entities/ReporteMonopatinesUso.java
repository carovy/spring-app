package org.example.reportsmicroservice.entities;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ReporteMonopatinesUso {
    private String nombre;
    private String descripcion;
    private Long idMonop;
    private float maxKm;
    private float tiempo;
    private float tiempoConPausas;

    public ReporteMonopatinesUso(Long id, float km, float t, float tP){
        this.nombre = "Reporte de uso de monopatines";
        this.descripcion = "El reporte provee una descripcion sobre el uso de monopatines en relacion los kilometros recorridos y el tiempo de uso (con o sin pausa)";
        this.idMonop = id;
        this.maxKm = km;
        this.tiempo = t;
        this.tiempoConPausas = tP;
    }

    @Override
    public String toString() {
            return "Nombre del reporte: " + this.nombre + "Descripcion: " + this.descripcion + " Id del monopatin: " + this.idMonop + " Cantidad de km recorridos: " + this.maxKm + " Tiempo max de uso: " + this.tiempo + " Tiempo max de uso con pausas: " + this.tiempoConPausas;

    }
}
