package com.example.demo.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.dtos.LoginUserDto;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UsuarioContext;
import com.example.demo.exceptions.loginExceptions.UsernameNoEncontradoException;

import jakarta.servlet.http.Cookie;

@Service
public class AuthenticationService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    public Usuario authenticate(LoginUserDto input) {
        Usuario usuario = usuarioService.encontrarUsuarioPorNombre(input.getUsername());
        if(usuario == null) throw new UsernameNoEncontradoException();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()));

        return usuario;
    }

    public Cookie generateCookieToken(Usuario usuario) {
        String token = jwtService.generateToken(usuario);
        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);

        return cookie;
    }

    public Cookie logoutWrapper () {
        Cookie cookie = new Cookie("jwtToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // si usas HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(0);

        SecurityContextHolder.clearContext();

        return cookie;
    }

    /*
     * Este método se hizo para que AuthenticationController solo inyectará
     * authenticationService
     * en vez de authenticationService y usuarioService
     */
    public UsuarioContext getUsuarioViewClientContext(Usuario usuario) {
        return usuarioService.convertirUsuarioAUsuarioView(usuario);
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
