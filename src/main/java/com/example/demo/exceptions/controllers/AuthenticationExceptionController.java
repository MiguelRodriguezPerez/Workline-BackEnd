package com.example.demo.exceptions.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions.loginExceptions.UsernameNoEncontradoException;

@RestControllerAdvice
public class AuthenticationExceptionController {

    @ExceptionHandler(UsernameNoEncontradoException.class)
    public ResponseEntity<?> wrongUsernameAdvice(UsernameNoEncontradoException ex){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    /*Esto se diseño para que cuando redux actualizará el contexto del usuario logueado
    pudierá evaluar el código http de respuesta y decidir si actualizar el estado del usuario o no.
    Antes no controlaba las excepciónes en la solicitud del login*/
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> wrongPasswordAdvice(BadCredentialsException ex){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
