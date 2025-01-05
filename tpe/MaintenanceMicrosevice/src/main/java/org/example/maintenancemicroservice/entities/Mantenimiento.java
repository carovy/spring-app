package org.example.maintenancemicroservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long id_monopatin;

    @Column(nullable = false)
    private String estado;

    public Mantenimiento(Long id_monopatin, String estado) {
        this.id_monopatin = id_monopatin;
        this.estado = estado;
    }

    public Mantenimiento() {

    }
}
