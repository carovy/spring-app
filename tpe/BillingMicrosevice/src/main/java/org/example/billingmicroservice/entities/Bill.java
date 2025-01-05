package org.example.billingmicroservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "billing")
public class Bill {
    @Id
    private String id;

    @Field("fecha_vigencia")
    private LocalDate fecha; // MongoDB almacenará esto con el nombre "fecha_vigencia"

    private float price;

    @Field("additional_price")
    private float additionalPrice; // Se almacenará como "additional_price"
}
