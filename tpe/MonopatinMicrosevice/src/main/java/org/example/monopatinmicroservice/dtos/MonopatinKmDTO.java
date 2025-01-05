package org.example.monopatinmicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonopatinKmDTO {
    private Long id;
    private Float kmRecorridos;
    private Integer tiempoUso;
    private Integer tiempoPausas;
}
