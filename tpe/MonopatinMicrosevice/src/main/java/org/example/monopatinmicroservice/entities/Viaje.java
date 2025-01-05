package org.example.monopatinmicroservice.entities;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @CsvBindByName
    private LocalDate fecha;

    @Column
    @CsvBindByName
    private Integer duracion;

    @Column
    @CsvBindByName
    private Long id_usuario;

    @ManyToOne
    @CsvBindByName
    private Monopatin monopatin;

    @Column
    private Float kilometros;
}
