package micro.example.gateway.service.dto.login;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {

    @NotNull( message = "El usuario es un campo requerido." )
    @NotEmpty( message = "El usuario es un campo requerido." )
    private String email;

    @NotNull( message = "La contraseña es un campo requerido." )
    @NotEmpty( message = "La contraseña es un campo requerido." )
    private String password;

    public String toString(){
        return "Username: " + email + ", Password: [FORBIDDEN] ";
    }
}