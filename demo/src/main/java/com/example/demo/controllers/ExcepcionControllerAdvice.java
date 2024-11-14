package com.example.demo.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exceptions.PagContrataIncorrectaException;
import com.example.demo.exceptions.PagOfertasPublicadasIncorrecta;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExcepcionControllerAdvice {
    
    @ExceptionHandler(PagContrataIncorrectaException.class)
    public void handleWrongContrataPag(HttpServletResponse http){
        try {
            http.sendRedirect("/seccionContrata/pagina/0");
        } catch (IOException ex) {
            System.out.println("IOException causada al gestionar PagContrataIncorrectaException " + ex.getMessage());
        }
        
    }

    @ExceptionHandler(PagOfertasPublicadasIncorrecta.class)
    public void handleWrongOfertasPublicadasPag(HttpServletResponse http){
        try {
            http.sendRedirect("/ofertasDeTrabajo/0");
        } catch (IOException ex) {
            System.out.println("IOException causada al gestionar PagOfertasPublicadasIncorrecta " + ex.getMessage());
        }
    }
}
