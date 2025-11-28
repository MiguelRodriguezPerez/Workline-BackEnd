package com.example.demo.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.dtos.LoginUserDto;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UserContextInterface;
import com.example.demo.exceptions.loginExceptions.UsernameNoEncontradoException;

import io.github.cdimascio.dotenv.Dotenv;
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
        if (usuario == null)
            throw new UsernameNoEncontradoException();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()));

        return usuario;
    }

    public ResponseCookie generateCookieToken(Usuario usuario) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String token = jwtService.generateToken(usuario);

        return ResponseCookie.from("jwtToken", token)
                .httpOnly(true)
                .secure(Boolean.parseBoolean(dotenv.get("SHOULD_JWT_COOKIE_BE_SECURE")))
                .path("/")
                .maxAge(30 * 30)
                /*
                 * Si la cookie es segura, significa que estas en prod. Si estas en prod
                 * tienes que poner SameSite=none porque si no el navegador rechazará cookies
                 * que no
                 * van al mismo dominio del que vinieron si son secure
                 * 
                 * Jakarta Cookie no permite esta configuración
                 */
                .sameSite(
                        Boolean.parseBoolean(dotenv.get("SHOULD_JWT_COOKIE_BE_SECURE")) ? "None" : "Lax")
                .build();
    }

    public ResponseCookie logoutWrapper() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        ResponseCookie logoutCookie = ResponseCookie.from("jwtToken", null)
                .httpOnly(true)
                .secure(Boolean.parseBoolean(dotenv.get("SHOULD_JWT_COOKIE_BE_SECURE")))
                .sameSite(
                        Boolean.parseBoolean(dotenv.get("SHOULD_JWT_COOKIE_BE_SECURE")) ? "None" : "Lax")
                .path("/")
                .maxAge(0)
                .build();

        SecurityContextHolder.clearContext();

        return logoutCookie;
    }

    /*
     * Este método se hizo para que AuthenticationController solo inyectará
     * authenticationService
     * en vez de authenticationService y usuarioService
     */
    public UserContextInterface getUsuarioViewClientContext(Usuario usuario) {
        return usuarioService.convertirUsuarioAUsuarioView(usuario);
    }

}
