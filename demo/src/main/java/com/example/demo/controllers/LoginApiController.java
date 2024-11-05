package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.entidadesApi.UserLoginRequest;
import com.example.demo.domain.usuarios.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/api/logins")
@RestController
public class LoginApiController {

    @Autowired
    UsuarioService usuarioService;
    
    @PostMapping("/login")
    public ResponseEntity<Usuario> postMethodName(@RequestBody UserLoginRequest user, HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = usuarioService.loginUsuario(user,request,response);
        if(usuario == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    } 

    @GetMapping("/logout")
    public ResponseEntity<?> postLogout() {
        if(usuarioService.logoutUsuario()) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/currentUser")
    public ResponseEntity<Usuario> getCurrentUser(){
        Usuario usuario = usuarioService.obtenerUsuarioConectado();
        System.out.println(usuario);
        if(usuario == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(usuario,HttpStatus.OK);
    }
}
