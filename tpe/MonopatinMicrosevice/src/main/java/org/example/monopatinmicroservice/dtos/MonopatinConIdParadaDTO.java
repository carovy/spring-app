package org.example.monopatinmicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinConIdParadaDTO {
    private Float kmRecorridos;
    private Long id_parada;
    private int posX;
    private int posY;
}