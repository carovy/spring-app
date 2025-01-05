package org.example.monopatinmicroservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "parada")
    @JoinColumn(nullable = true)
    @JsonBackReference
    @CsvBindByName
    private Monopatin monopatin;

    @Getter
    @Column(nullable = false)
    private boolean habilitada;

}
