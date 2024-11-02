package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.entidadesApi.UserLoginRequest;
import com.example.demo.domain.usuarios.Usuario;

import jakarta.annotation.Resource;

@RequestMapping("/api/logins")
@RestController
public class LoginApiController {

    @Autowired
    UsuarioService usuarioService;
    
    @PostMapping("/login")
    public ResponseEntity<Usuario> postMethodName(@RequestBody UserLoginRequest user) {
        Usuario usuario = usuarioService.loginUsuario(user);
        if(usuario == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    } 

    @GetMapping("/logout")
    public ResponseEntity<?> postLogout() {
        if(usuarioService.logoutUsuario()) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    

}
