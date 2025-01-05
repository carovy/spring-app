package micro.example.gateway.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import micro.example.gateway.DTO.UsuarioRequestDto;
import micro.example.gateway.DTO.UsuarioResponseDto;
import micro.example.gateway.FeignClient.UsuarioFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;


    @Autowired
    UsuarioFeignClient usuarioFeignClient;

    public UsuarioResponseDto saveUser(UsuarioRequestDto usuarioRequestDto) throws Exception {
        try{
            final var user = new UsuarioRequestDto();
            user.setId(usuarioRequestDto.getId());
            user.setApellido(usuarioRequestDto.getApellido());
            user.setNombre(usuarioRequestDto.getNombre());
            user.setEmail(usuarioRequestDto.getEmail());
            user.setId_rol(usuarioRequestDto.getId_rol());
            user.setNumeroCelular(usuarioRequestDto.getNumeroCelular());
            user.setPassword( passwordEncoder.encode( usuarioRequestDto.getPassword()));
            return this.usuarioFeignClient.save(user);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
