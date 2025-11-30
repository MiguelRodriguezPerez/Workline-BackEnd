package com.example.demo.services.ofertas;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.OfertaDto;
import com.example.demo.domain.usuarios.busca.Busca;
import com.example.demo.domain.usuarios.contrata.Contrata;

public interface OfertaService {

    Oferta guardarOferta(Oferta oferta);

    OfertaDto guardarNuevaOferta(OfertaDto ofertaDto);

    OfertaDto actualizarOferta(OfertaDto ofertaDto);

    Oferta obtenerPorId(Long id);

    List<Oferta> obtenerTodos();

    void borrarOferta(Long id);

    void borrarOfertaWrapper(Long id);

    void borrarTodosCandidatosDeUnaOferta(Long id);

    void borrarBuscaDeTodasLasOfertas(Busca busca);

    void borrarTodasLasOfertasDeUnContrata(Contrata contrata);

    void borrarTodosCandidatosTodasOfertasFromContrataId(Contrata contrata);

    Page<OfertaDto> obtenerPaginaOfertas(int pagina, BusquedaOferta busquedaOferta);

    List<Oferta> obtenerResultados(BusquedaOferta busquedaOferta);

    boolean estaSuscritoOferta(Long id);

    void inscribirBuscaConectadoWrapper(Long id);

    void desinscribirBuscaConectadoWrapper(Long id);

}
