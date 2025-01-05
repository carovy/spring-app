package org.example.monopatinmicroservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Monopatin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @CsvBindByName
    private Float kmRecorridos;

    @Column
    @CsvBindByName
    private Integer tiempoUso;

    @Column
    @CsvBindByName
    private Integer tiempoUsoConPausas;

    @OneToOne
    @JoinColumn(nullable = true)
    @JsonManagedReference
    @CsvBindByName
    private Parada parada;

    @Column
    @CsvBindByName
    private int posX;

    @Column
    @CsvBindByName
    private int posY;

    @Override
    public String toString() {
        return "Id: "+ this.getId() + ", KmRecorridos: "+ this.getKmRecorridos()+", tiempoUso: "+this.getTiempoUso()+", tiempo con pausas: "+this.getTiempoUsoConPausas()+", posX: "+this.getPosX()+", posY: "+this.getPosY();
    }
}
