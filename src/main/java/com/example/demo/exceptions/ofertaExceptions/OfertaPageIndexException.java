package com.example.demo.exceptions.ofertaExceptions;

import com.example.demo.domain.entidadesApi.PaginaJobSearchRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage"})
public class OfertaPageIndexException extends RuntimeException {
    
    private final PaginaJobSearchRequest request;

    public OfertaPageIndexException(PaginaJobSearchRequest request) {
        super("The requested page does not exist");
        this.request = request; 
    }
}