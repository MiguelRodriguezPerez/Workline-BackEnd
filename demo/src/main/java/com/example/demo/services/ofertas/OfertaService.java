package com.example.demo.services.ofertas;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.OfertaDtoApi;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;

public interface OfertaService {

    Oferta guardarOferta(Oferta oferta);

    Oferta guardarOfertaFromContrata(Oferta oferta);

    Oferta guardarCambios(Oferta oferta);

    Oferta obtenerPorId(Long id);

    Oferta convertirOfertaDtoApiAOferta(OfertaDtoApi ofertaDtoApi);

    void borrarOferta(Long id);

    void borrarOfertaWrapper(Long id);

    void borrarTodosCandidatosDeUnaOferta(Long id);

    void borrarBuscaDeTodasLasOfertas(Busca busca);

    void borrarTodasLasOfertasDeUnContrata(Contrata contrata);

    List<Oferta> obtenerTodos();

    Page<Oferta> obtenerPaginaApi(int pagina, BusquedaOferta busquedaOferta);

    List<Oferta> obtenerResultados(BusquedaOferta busquedaOferta);

    void cambiarPropiedadOfertas(List<Oferta> listaOfertas, String nombre);

    boolean estaSuscritoOferta(Long id);

    void inscribirBuscaConectadoWrapper(Long id);

    void desinscribirBuscaConectadoWrapper(Long id);

    int obtenerNumeroCandidatos(Long id);

}
