package org.example.usermicroservice.services;

import org.example.usermicroservice.DTO.UsuarioRequestDto;
import org.example.usermicroservice.DTO.UsuarioResponseDto;
import org.example.usermicroservice.DTO.userRoleDTO;
import org.example.usermicroservice.entities.Role;
import org.example.usermicroservice.entities.User;
import org.example.usermicroservice.feignClients.MonopatinFeignClient;
import org.example.usermicroservice.repositories.RoleRepository;
import org.example.usermicroservice.repositories.UserRepository;
import org.example.usermicroservice.utils.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    MonopatinFeignClient monopatinFeignClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public List<userRoleDTO> getUsersByRole(String role) {
        try {
            UserRoles userRole = UserRoles.valueOf(role.toLowerCase());
            List<User> result = this.userRepository.getUsersByRole(userRole.name());
            List<userRoleDTO> users = new ArrayList<>();
            for(User u : result){
                users.add(new userRoleDTO(u.getId(), u.getName(), u.getLastname(), u.getRole().getRole()));
            }
            return users;
        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }
    }

    public UsuarioResponseDto save(UsuarioRequestDto user){
        UsuarioResponseDto responseDto = new UsuarioResponseDto();
        User usuario = this.mapearDtoAEntididad(user);

        this.userRepository.save(usuario);
        return this.mapearEntididadADto(usuario);
    }

    private UsuarioResponseDto mapearEntididadADto(User usuario) {
        UsuarioResponseDto responseDto = new UsuarioResponseDto();

        responseDto.setId(usuario.getId());
        responseDto.setNombre(usuario.getName());
        responseDto.setApellido(usuario.getLastname());
        responseDto.setEmail(usuario.getEmail());
        responseDto.setNumeroCelular(usuario.getPhoneNumber());
        responseDto.setPassword(usuario.getPassword());

        // Mapear Rol
        if (usuario.getRole() != null) {
            Role rol = new Role();
            rol.setId(usuario.getRole().getId());
            rol.setRole(usuario.getRole().getRole());
            responseDto.setRole(rol);
        }

        return responseDto;
    }

    private User mapearDtoAEntididad(UsuarioRequestDto UsuarioRequestDto) {
        User usuario = new User();
        usuario.setName(UsuarioRequestDto.getNombre());
        usuario.setLastname(UsuarioRequestDto.getApellido());
        usuario.setEmail(UsuarioRequestDto.getEmail());
        usuario.setPassword(UsuarioRequestDto.getPassword());
        usuario.setPhoneNumber(UsuarioRequestDto.getNumeroCelular());
        usuario.setRole(this.roleRepository.getById(UsuarioRequestDto.getId_rol()));
        return usuario;
    }

    public void delete(Long user_id){
        userRepository.deleteById(user_id);
    }

    public User getUserById(Long id){

        return userRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> getClosestMonopatins(int posx, int posy) {
        ResponseEntity<?> monopatins = this.monopatinFeignClient.getClosestMonopatins(posx, posy);
        return monopatins;
    }

    public User getUserByEmail(String email) {
        ArrayList<User> result = this.userRepository.getUserByEmail(email);
        if(!result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }
}
