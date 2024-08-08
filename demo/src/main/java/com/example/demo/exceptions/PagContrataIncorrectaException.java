package com.example.demo.exceptions;

public class PagContrataIncorrectaException extends RuntimeException {
    private static final String mensaje = "Pagina contrata incorrecta";
    
    public PagContrataIncorrectaException(){
        super(mensaje);
    }
}
