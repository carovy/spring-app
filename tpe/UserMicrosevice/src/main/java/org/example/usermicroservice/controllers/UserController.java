package org.example.usermicroservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.usermicroservice.DTO.UsuarioRequestDto;
import org.example.usermicroservice.DTO.UsuarioResponseDto;
import org.example.usermicroservice.DTO.userRoleDTO;
import org.example.usermicroservice.entities.User;
import org.example.usermicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping //Andando
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}") //Andando
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        try{
            User user = userService.getUserById(id);
            if (user == null) {
                return  ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return  ResponseEntity.status(500).build();
        }

    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody UsuarioRequestDto usuario){
        try{
            UsuarioResponseDto userNew = userService.save(usuario);
            return ResponseEntity.ok(userNew);
        }catch(Exception e){
            return  ResponseEntity.status(500).build();
        }
    }

    //ANDA, OBTIENE MONOPATINES EN EL RADIO DE UN KILOMETRO
    @GetMapping("/monopatins/location/{posx}/{posy}")
    public ResponseEntity<?> getClosestMonopatins(@PathVariable("posx") int posx, @PathVariable("posy") int posy){
        return this.userService.getClosestMonopatins(posx, posy);
    }

    @GetMapping("/role/{role}") //Andando
    public ResponseEntity<?> getUsersByRole(@PathVariable("role") String role){
        List<userRoleDTO> users = this.userService.getUsersByRole(role);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("/email/{userEmail}")
    public UsuarioResponseDto getUserByUsername(@PathVariable("userEmail") String userEmail){
        User u = this.userService.getUserByEmail(userEmail);
        if(u == null){
            return null;
        }
        UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();
        usuarioResponseDto.setId(u.getId());
        usuarioResponseDto.setNombre(u.getName());
        usuarioResponseDto.setApellido(u.getLastname());
        usuarioResponseDto.setNumeroCelular(u.getPhoneNumber());
        usuarioResponseDto.setPassword(u.getPassword());
        usuarioResponseDto.setRole(u.getRole());
        usuarioResponseDto.setEmail(u.getEmail());

        return usuarioResponseDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        this.userService.delete(id);
    }
}
