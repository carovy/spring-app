package micro.example.gateway.security;

import micro.example.gateway.DTO.UsuarioResponseDto;
import micro.example.gateway.FeignClient.UsuarioFeignClient;
import micro.example.gateway.Model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) {

        UsuarioResponseDto user = usuarioFeignClient.getUserByUsername(username.toLowerCase());

        if (user == null) {
            throw new UsernameNotFoundException("El usuario " + username + " no existe");
        }
        Usuario u = new Usuario();
        u.setId(user.getId());
        u.setNombre(user.getNombre());
        u.setApellido(user.getApellido());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setRol(user.getRole());
        return createSpringSecurityUser(u);
    }

    private UserDetails createSpringSecurityUser(Usuario user) {
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
