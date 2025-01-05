package micro.example.gateway.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.example.gateway.Model.Role;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private Long numeroCelular;
    private String email;
    private String password;
    private Role role;

    private String mensaje;
    private boolean exito;
}
