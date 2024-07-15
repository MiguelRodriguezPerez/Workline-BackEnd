package com.example.demo.services.ofertas;

import java.util.TreeMap;

import com.example.demo.domain.ofertas.Oferta;

public interface BusquedaOfertaService {
    
    TreeMap<Long,Oferta> obtenerResultados(String orden);
}
