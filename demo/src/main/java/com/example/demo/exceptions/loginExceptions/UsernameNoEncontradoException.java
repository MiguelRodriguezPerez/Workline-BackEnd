package com.example.demo.exceptions.loginExceptions;

public class UsernameNoEncontradoException extends RuntimeException{
    private static final String mensaje = "Nombre de usuario no encontrado";
    
    public UsernameNoEncontradoException(){
        super(mensaje);
    }
}
