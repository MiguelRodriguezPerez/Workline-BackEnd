package com.example.demo.controllers.nuevoUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UsuarioService;

//Esta es la api que teóricamente debería válidar si un nombre de usuario es repetido o no
@RequestMapping("/nuevoUsuarioCreacion")
@RestController
public class NuevoUsuarioApi {

    @Autowired
    UsuarioService usuarioService;
    //Este es el mapeado con el que debería enlazar validarUsuario.js
    @GetMapping("/esRepetido/{nombre}")
    public ResponseEntity<Boolean> isUsernameRepeated(@PathVariable String nombre){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(usuarioService.esNombreRepetido(nombre));
    }
}
