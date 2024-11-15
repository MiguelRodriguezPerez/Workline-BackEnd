package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.Experiencia;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UsuarioContext;
import com.example.demo.domain.usuarios.UsuarioDto;
import com.example.demo.services.usuarios.BuscaService;

@RequestMapping("/user")
@RestController
public class MiPerfilController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    BuscaService buscaService;

    @GetMapping("/getCurrentUser")
    public ResponseEntity<UsuarioContext> getLoggedUser() {
        // El token llega
        Usuario usuario = usuarioService.obtenerUsuarioLogueado();
        UsuarioContext usuarioView = usuarioService.convertirUsuarioAUsuarioView(usuario);

        return new ResponseEntity<>(usuarioView, HttpStatus.OK);
    }

    @GetMapping("/getUserData")
    public ResponseEntity<UsuarioDto> getUserData() {
        UsuarioDto usuarioDto = usuarioService.convertirUsuarioAUsuarioDto(usuarioService.obtenerUsuarioLogueado());
        return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
    }

    @PutMapping("/updateUserData")
    public ResponseEntity<UsuarioContext> updateUserData(@RequestBody UsuarioDto usuarioDto) {
        usuarioService.guardarCambios(usuarioDto);

        UsuarioContext resultado = usuarioService.convertirUsuarioAUsuarioView(usuarioService.obtenerUsuarioLogueado());
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }


}
