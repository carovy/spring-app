package org.example.usermicroservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.usermicroservice.entities.Role;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String numeroCelular;
    private String email;
    private String password;
    private Role role;

    private String mensaje;
    private boolean exito;
}
