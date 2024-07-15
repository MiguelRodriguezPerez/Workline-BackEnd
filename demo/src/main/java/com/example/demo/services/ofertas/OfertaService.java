package com.example.demo.services.ofertas;

import java.util.List;
import java.util.TreeMap;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;

public interface OfertaService {
    
    Oferta guardarOferta(Oferta oferta);
    void borrarOferta(Long id);
    Oferta obtenerPorId(Long id);
    List<Oferta> obtenerTodos();
    List<Oferta> obtenerResultados(BusquedaOferta busquedaOferta);
    List<Oferta> obtenerPagina(Integer paginaElecta, BusquedaOferta busquedaOferta);
    int existeSiguientePagina(Integer paginaElecta, BusquedaOferta busquedaOferta);
    int existeAnteriorPagina(Integer paginaElecta, BusquedaOferta busquedaOferta);
    int obtenerNumeroPaginas(BusquedaOferta busquedaOferta);
    void cambiarPropiedadOfertas();
    boolean coincidenEstudios(Oferta oferta, BusquedaOferta busquedaOferta);
}
