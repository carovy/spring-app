package org.example.usermicroservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "role")  // La propiedad "rol" de la entidad Usuario
    @JsonManagedReference
    private List<User> users;


}
