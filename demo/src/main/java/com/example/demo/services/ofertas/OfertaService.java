package com.example.demo.services.ofertas;

import java.util.TreeMap;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;

public interface OfertaService {
    
    Oferta guardarOferta(Oferta oferta);
    void borrarOferta(Long id);
    Oferta obtenerPorId(Long id);
    TreeMap<Long,Oferta> obtenerTodos();
    TreeMap<Long,Oferta> obtenerResultados(BusquedaOferta busquedaOferta);
    TreeMap<Long,Oferta> obtenerPagina(Integer paginaElecta, BusquedaOferta busquedaOferta);
    int existeSiguientePagina(Integer paginaElecta, BusquedaOferta busquedaOferta);
    int existeAnteriorPagina(Integer paginaElecta, BusquedaOferta busquedaOferta);
    int obtenerNumeroPaginas(BusquedaOferta busquedaOferta);
    void cambiarPropiedadOfertas();
}
