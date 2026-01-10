package com.example.demo.exceptions.ofertaExceptions;

public class OfertaIdNotFoundException extends RuntimeException {
    
    public Long faultyId;

    public OfertaIdNotFoundException (Long id) {
        super("This oferta id does not exist in the database");
        this.faultyId = id;
    }
}
