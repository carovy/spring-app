package org.example.usermicroservice.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.usermicroservice.entities.User;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDto {
    private Long id; // Número identificatorio

    @NotEmpty(message = "el campo nombre de la carrera no puede estar vacío")
    @NotNull( message = "El nombre es un campo obligatorio.")
    private String nombre; // Nombre del usuario

    @NotEmpty(message = "el campo apellido  no puede estar vacío")
    @NotNull( message = "El apellido es un campo obligatorio.")
    private String apellido; // Apellido del usuario

    @NotNull( message = "El numero de celular es un campo obligatorio.")
    private String numeroCelular; // Número de celular

    @NotEmpty(message = "el email nombre de la carrera no puede estar vacío")
    @NotNull( message = "El email es un campo obligatorio.")
    private String email; // Email válido

    private Long id_rol;

    private String password;
}
