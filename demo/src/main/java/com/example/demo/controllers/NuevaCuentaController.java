package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.dtos.LoginUserDto;
import com.example.demo.domain.dtos.NuevoUsuarioDto;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UsuarioContext;
import com.example.demo.services.auth.AuthenticationService;
import com.example.demo.services.usuarios.ContrataService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/nuevaCuenta/api")
public class NuevaCuentaController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ContrataService contrataService;


    @GetMapping("/esNombreRepetido/{nombre}")
    public ResponseEntity<Boolean> isUserRepeated(@PathVariable String nombre){
        //Sospechoso de fallar
        Boolean resultado = usuarioService.esNombreRepetido(nombre);
        System.out.println(resultado + "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
    

    //Discutiblemente podría ser PutMapping
    @PostMapping("/nuevoContrata")
    public ResponseEntity<UsuarioContext> postMethodName(@RequestBody NuevoUsuarioDto dto, HttpServletResponse response) {

        contrataService.guardarNuevoUsuarioFromDto(dto);
        //Asumiendo que todo vaya bien
        //Usas la contraseña del dto sin encriptar. La encriptada no serviría
        Usuario authenticatedUser = authenticationService.authenticate( 
            new LoginUserDto(dto.getNombre(), dto.getPassword())
        );
        response.addCookie(authenticationService.generateCookieToken(authenticatedUser));

        UsuarioContext usuarioContext = authenticationService.getUsuarioViewClientContext(authenticatedUser);
        
        return new ResponseEntity<>(usuarioContext, HttpStatus.OK);
    }
    
}
