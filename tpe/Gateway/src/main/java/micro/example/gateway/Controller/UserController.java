package micro.example.gateway.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import micro.example.gateway.DTO.UsuarioRequestDto;
import micro.example.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto) throws Exception {
        return ResponseEntity.status(201).body(userService.saveUser(usuarioRequestDto));
    }
}
