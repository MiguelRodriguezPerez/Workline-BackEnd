package com.example.demo.exceptions;

public class PagOfertasPublicadasIncorrecta extends RuntimeException{
    private final static String mensaje = "Pagina ofertas publicadas incorrecta";

    public PagOfertasPublicadasIncorrecta(){
        super(mensaje);
    }
}
