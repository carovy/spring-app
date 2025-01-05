package micro.example.gateway.config;

import micro.example.gateway.security.AuthotityConstant;
import micro.example.gateway.security.jwt.JwtFilter;
import micro.example.gateway.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain( final HttpSecurity http ) throws Exception {
        http
            .csrf( AbstractHttpConfigurer::disable );
        http
            .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
            .securityMatcher("/**")
            .authorizeHttpRequests( authz -> authz
                    .requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                    .requestMatchers(HttpMethod.GET,"/admin/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.POST,"/admin/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.PUT,"/admin/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.POST,"/monopatin/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.DELETE,"/monopatin/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.POST,"/parada/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.DELETE,"/parada/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.POST,"/pausa/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.DELETE,"/pausa/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.POST,"/viaje/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.DELETE,"/viaje/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.GET,"/accounts/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.PUT,"/accounts/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.POST,"/accounts/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.GET,"/roles/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.POST,"/roles/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.GET,"/users/**").hasAuthority(AuthotityConstant._USUARIO)
                    .requestMatchers(HttpMethod.DELETE,"/users/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.GET,"/mantenimiento/**").hasAuthority(AuthotityConstant._MANTENIMIENTO)
                    .requestMatchers(HttpMethod.POST,"/mantenimiento/**").hasAuthority(AuthotityConstant._MANTENIMIENTO)
                    .requestMatchers(HttpMethod.PUT,"/mantenimiento/**").hasAuthority(AuthotityConstant._MANTENIMIENTO)
                    .requestMatchers(HttpMethod.GET,"/bills/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    .requestMatchers(HttpMethod.POST,"/bills/**").hasAuthority(AuthotityConstant._ADMINISTRADOR)
                    //no se hace una autenticación para reporte porque los mismos solo son llamados desde otros microservicios
                    .anyRequest().authenticated()
            )
            .httpBasic( Customizer.withDefaults() )
            .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

}
