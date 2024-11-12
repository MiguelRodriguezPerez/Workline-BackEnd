package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.dtos.LoginUserDto;
import com.example.demo.domain.dtos.RegisterUserDto;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UsuarioView;

import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.Cookie;

@Service
public class AuthenticationService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtService jwtService;

    public Usuario signup(RegisterUserDto input) {
        //TODO: Creación usuarios
        throw new UnsupportedJwtException("AAAAAAAAAAAAAAAAAAAAAAAA");
    }

    public Usuario authenticate(LoginUserDto input) {
        Usuario usuario = usuarioService.encontrarUsuarioPorNombre(input.getUsername());
        if(usuario == null) return null;

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                ));

        return usuario;
    }

    public Cookie generateCookieToken(Usuario usuario){
        String token = jwtService.generateToken(usuario);
        Cookie cookie = new Cookie("jwtToken", token); 
        cookie.setHttpOnly(true); 
        cookie.setSecure(true); 
        cookie.setPath("/"); 
        cookie.setMaxAge(3600);

        return cookie;
    }

    /*Este método se hizo para que AuthenticationController solo inyectará authenticationService
    en vez de authenticationService y usuarioService*/
    public UsuarioView getUsuarioViewClientContext(Usuario usuario){
        return usuarioService.convertirUsuarioAUsuarioView(usuario);
    }

    public void logout(){
        SecurityContextHolder.clearContext();
    }
}
