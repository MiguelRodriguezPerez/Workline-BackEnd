package com.example.demo.exceptions.ofertaExceptions;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage"})
public class NoOfertaResultsFoundException extends RuntimeException {

    public BusquedaOferta busquedaOferta;
    
    public NoOfertaResultsFoundException (BusquedaOferta busquedaOferta) {
        super("No results found with following parameters");
        this.busquedaOferta = busquedaOferta;
    }
}
