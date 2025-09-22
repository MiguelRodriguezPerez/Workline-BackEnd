package com.example.demo.domain.ofertas;

public enum TipoContrato {
    INDEFINIDO,DISCONTINUO,TEMPORAL;

    /* Este método generado a mano te permite obtener un enum a partir de un valor númerico entre 0 y 2 */
    public static TipoContrato fromInt(int valor) {
        return TipoContrato.values()[valor]; 
    }
}


