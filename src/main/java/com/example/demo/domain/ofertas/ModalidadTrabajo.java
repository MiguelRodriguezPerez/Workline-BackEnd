package com.example.demo.domain.ofertas;

public enum ModalidadTrabajo {
    PRESENCIAL,HIBRIDO,TELETRABAJO;

    /* Este método generado a mano te permite obtener un enum a partir de un valor númerico entre 0 y 2 */

    public static ModalidadTrabajo fromInt(int valor) {
        return ModalidadTrabajo.values()[valor]; 
    }
}
