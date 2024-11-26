package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dtos.LoginUserDto;
import com.example.demo.domain.dtos.RegisterUserDto;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UsuarioContext;
import com.example.demo.services.auth.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioContext> authenticate(@RequestBody LoginUserDto loginUserDto,
            HttpServletResponse response) {
        Usuario authenticatedUser = authenticationService.authenticate(loginUserDto);
        response.addCookie(authenticationService.generateCookieToken(authenticatedUser));

        UsuarioContext usuarioView = authenticationService.getUsuarioViewClientContext(authenticatedUser);

        return ResponseEntity.ok(usuarioView);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> triggerLogout(HttpServletRequest request) {
        System.out.println(request.getCookies());
        authenticationService.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
