package org.example.billingmicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private LocalDate fechaInicioFacturacionNueva;
    private float precioFijo;
    private float precioExtra;
}
