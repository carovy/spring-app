package org.example.billingmicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDTO {
    private Long id;
    private LocalDate fecha;
    private Integer duracion;
    private Long id_usuario;
    private MonopatinDTO monopatin;
    private Float kilometros;
}
