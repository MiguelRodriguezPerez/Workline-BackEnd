package com.example.demo.services.ofertas;

import java.util.List;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;

public interface OfertaService {
    
    Oferta guardarOferta(Oferta oferta);
    void borrarOferta(Long id);
    Oferta obtenerPorId(Long id);
    List<Oferta> obtenerTodos();
    List<Oferta> obtenerResultados(BusquedaOferta busquedaOferta);
    List<Oferta> obtenerPagina(Integer paginaElecta, BusquedaOferta busquedaOferta);
    boolean existeSiguientePagina(Integer paginaElecta, String busquedaOferta);
    int existeAnteriorPagina(Integer paginaElecta);
    int obtenerNumeroPaginas(BusquedaOferta busquedaOferta);
    void cambiarPropiedadOfertas();
    boolean coincidenEstudios(Oferta oferta, BusquedaOferta busquedaOferta);
}
