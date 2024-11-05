package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*DISCLAIMER: Téoricamente esto evita los problemas relacionados con CORS entre el lado
    cliente y el lado servidor. Pero no estoy convencido. Esto anula cors*/
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173"); // Aquí pones el origen de tu frontend (ajustar según sea necesario)
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean 
    public SecurityContextRepository securityContextRepository() { 
        return new HttpSessionSecurityContextRepository(); // O cualquier implementación que necesites
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.headers(headersConfigurer -> headersConfigurer.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/ofertasDeTrabajo/inscribirse/**", "/ofertasDeTrabajo/desinscribirse/**").hasRole("BUSCA")
            .requestMatchers("/seccionContrata/**").hasRole("CONTRATA")
            .requestMatchers("/miPerfil/contrata/**").hasRole("CONTRATA")
            .requestMatchers("/miPerfil/busca/**").hasRole("BUSCA")
            .requestMatchers("/", "/ofertasDeTrabajo/**", "/solicitudOfertas/**", "/nuevoUsuario/**", 
            "/nuevoUsuarioCreacion/**", "/sesion/**", "/internal-api/public/**","/api/logins/**", "/get-csrf-token").permitAll()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .anyRequest().authenticated());


        http.securityContext((securityContext) -> securityContext
            .securityContextRepository(securityContextRepository())
            .requireExplicitSave(true));
        
        
        http.formLogin(formLogin -> formLogin
            .loginPage("/sesion/signin")
            .loginProcessingUrl("/login")
            .failureUrl("/sesion/signin?error=true")
            .defaultSuccessUrl("/", false)
            .permitAll());

        http.logout(logout -> logout
            .logoutSuccessUrl("/")
            .permitAll());

        http.exceptionHandling(exceptions -> {
            exceptions.accessDeniedPage("/sesion/error");
        });

        http.csrf(csrf -> csrf.disable());

        return http.build();
    } 
    

}


