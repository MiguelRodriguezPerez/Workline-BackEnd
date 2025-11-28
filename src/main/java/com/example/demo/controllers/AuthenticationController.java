package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dtos.LoginUserDto;
import com.example.demo.domain.usuarios.usuario.UserContextInterface;
import com.example.demo.domain.usuarios.usuario.Usuario;
import com.example.demo.services.auth.AuthenticationService;
import com.example.demo.services.auth.JwtService;
import com.example.demo.services.usuarios.usuario.UsuarioService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    JwtService jwtService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UserContextInterface> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Usuario authenticatedUser = authenticationService.authenticate(loginUserDto);
        UserContextInterface usuarioView = authenticationService.getUsuarioViewClientContext(authenticatedUser);
        ResponseCookie cookie = authenticationService.generateCookieToken(authenticatedUser);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(usuarioView);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> triggerLogout() {
        ResponseCookie logoutCookie = authenticationService.logoutWrapper();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, logoutCookie.toString())
                .body(null);
    }

    @GetMapping("/areCredentialsValid")
    public ResponseEntity<Boolean> areCredentialsValidEndpoint(
        @CookieValue(name = "jwtToken", required = false) String token) {
        return ResponseEntity.ok().body(jwtService.isTokenValid(token));
    }
}
