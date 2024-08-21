package com.example.demo.services.ofertas;

import java.util.List;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;

public interface OfertaService {
    
    Oferta guardarOferta(Oferta oferta);
    Oferta guardarOfertaFromContrata(Oferta oferta);
    Oferta guardarCambios(Oferta oferta);
    void borrarOferta(Long id);
    void borrarCandidatosOferta(Long id);
    void borrarBuscaTodasOfertas(Busca busca);
    void borrarContrataTodasOfertas(Contrata contrata);
    Oferta obtenerPorId(Long id);
    List<Oferta> obtenerTodos();
    List<Oferta> obtenerResultados(BusquedaOferta busquedaOferta);
    List<Oferta> obtenerPagina(Integer paginaElecta, BusquedaOferta busquedaOferta);
    boolean existeSiguientePagina(Integer paginaElecta, String busquedaOferta);
    int existeAnteriorPagina(Integer paginaElecta);
    int obtenerNumeroPaginas(BusquedaOferta busquedaOferta);
    void cambiarPropiedadOfertas(List<Oferta> listaOfertas, String nombre);
    List<Integer> obtenerListaPaginas(BusquedaOferta busquedaOferta, Integer numPag);
    boolean estaSuscritoOferta(Long id);
    
}
