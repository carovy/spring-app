package micro.example.gateway.FeignClient;

import micro.example.gateway.DTO.UsuarioRequestDto;
import micro.example.gateway.DTO.UsuarioResponseDto;
import micro.example.gateway.Model.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "UserMicroservice", url = "http://localhost:8011")
public interface UsuarioFeignClient {

    @PostMapping("/users")
    UsuarioResponseDto save(@RequestBody UsuarioRequestDto usuario);

    @GetMapping("/role/{role}")
    Role getRolById(@PathVariable("role") String role);

    @GetMapping("/users/email/{userEmail}")
    UsuarioResponseDto getUserByUsername(@PathVariable("userEmail") String userEmail);
}
