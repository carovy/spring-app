package org.example.billingmicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinDTO {
    private Long id;

    private Float kmRecorridos;
    private Float tiempoUso;
    private Float tiempoUsoConPausas;

    private ParadaDTO parada;

    private int posX;
    private int posY;
}
