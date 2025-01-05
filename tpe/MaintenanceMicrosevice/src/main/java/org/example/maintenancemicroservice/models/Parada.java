package org.example.maintenancemicroservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

public class Parada {
    private Long id;

    private Monopatin monopatin;
}
