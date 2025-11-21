package com.example.demo.services.ofertas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.OfertaDto;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.repositories.OfertaRepository;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    OfertaRepository repo;

    @Autowired
    ContrataService contrataService;

    @Autowired
    BuscaService buscaService;

    @Autowired
    BusquedaOfertaService busquedaOfertaService;

    @Override
    public Oferta guardarOferta(Oferta oferta) {
        return repo.save(oferta);
    }

    @Override
    public OfertaDto guardarNuevaOferta(OfertaDto ofertaDto) {
        /* Este método no mapea su relación con el usuario contrata logueado */
        Oferta oferta = this.convertirNuevaOfertaDtoAEntidad(ofertaDto);
        Contrata contrata = contrataService.obtenerContrataConectado();

        oferta.setContrata(contrata);
        contrata.getListaOfertas().add(oferta);

        this.guardarOferta(oferta);
        contrataService.guardarSinEncriptar(contrata);

        return this.convertirEntidadOfertaADto(oferta);
    }

    @Override
    public OfertaDto actualizarOferta(OfertaDto ofertaDto) {
        Oferta oferta = this.obtenerPorId(ofertaDto.getId());

        oferta.setPuesto(oferta.getPuesto());
        oferta.setSector(oferta.getSector());
        oferta.setModalidadTrabajo(oferta.getModalidadTrabajo());
        oferta.setDescripcion(oferta.getDescripcion());
        oferta.setSalarioAnual(oferta.getSalarioAnual());
        oferta.setTipoContrato(oferta.getTipoContrato());
        oferta.setHoras(oferta.getHoras());
        oferta.setFechaPublicacion(oferta.getFechaPublicacion());
        oferta.setCiudad(oferta.getCiudad());

        this.guardarOferta(oferta);

        return this.convertirEntidadOfertaADto(oferta);
    }

    @Override
    public void borrarOferta(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void borrarTodosCandidatosTodasOfertasFromContrataId(Contrata contrata) {
        repo.deleteAllCandidatosFromContrataId(contrata.getId());
    }

    /*
     * Necesitas este método para garantizar que en la sesión ambas entidades de la
     * relación se borren
     */
    @Override
    public void borrarOfertaWrapper(Long id) {
        Oferta oferta = this.obtenerPorId(id);

        if (!oferta.getListaCandidatos().isEmpty())
            repo.removeAllCandidatesFromOferta(id);

        /* No tienes que borrar ambos lados de las relaciones si usas sql "directo" */
        repo.deleteById(id);
    }

    @Override
    public void borrarTodosCandidatosDeUnaOferta(Long id) {
        repo.removeAllCandidatesFromOferta(id);
    }

    @Override
    public void borrarBuscaDeTodasLasOfertas(Busca busca) {
        repo.deleteBuscaFromAllOfertas(busca.getId());
    }

    @Override
    public void borrarTodasLasOfertasDeUnContrata(Contrata contrata) {
        repo.deleteAllOfertasByContrataId(contrata.getId());
    }

    @Override
    public Oferta obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Oferta> obtenerTodos() {
        return repo.findAll();
    }

    private final Integer ofertasPorPagina = 10;

    @Override
    public Page<OfertaDto> obtenerPaginaOfertas(int numPag, BusquedaOferta busquedaOferta) {
        List<OfertaDto> resultadosBusqueda = this.obtenerResultados(busquedaOferta)
            .stream()
            .map(this::convertirEntidadOfertaADto)
            .toList();

        Pageable paginable = PageRequest.of(numPag, ofertasPorPagina);
        int primeraOferta = (int) paginable.getOffset();
        int ultimaOferta = Math.min(primeraOferta + paginable.getPageSize(), resultadosBusqueda.size());

        Page<OfertaDto> resultado = new PageImpl<>(resultadosBusqueda.subList(primeraOferta, ultimaOferta), paginable,
                resultadosBusqueda.size());
        return resultado;
    }

    @Override
    public List<Oferta> obtenerResultados(BusquedaOferta busquedaOferta) {
        List<Oferta> resultado = this.obtenerTodos();

        Iterator<Oferta> iterator = resultado.iterator();

        while (iterator.hasNext()) {
            Oferta ofertaIteracion = iterator.next();

            if (!busquedaOferta.getPuesto().equalsIgnoreCase("")
                    && !busquedaOferta.getPuesto().equalsIgnoreCase(ofertaIteracion.getPuesto())) {
                iterator.remove();
                continue;
            }

            if (busquedaOferta.getTipoContrato() != null
                    && busquedaOferta.getTipoContrato() != ofertaIteracion.getTipoContrato()) {
                iterator.remove();
                continue;
            }

            if (!busquedaOferta.getCiudad().equalsIgnoreCase("")
                    && !busquedaOferta.getCiudad().equalsIgnoreCase(ofertaIteracion.getCiudad())) {
                iterator.remove();
                continue;
            }

            if (busquedaOferta.getSalarioAnualMinimo() != null && busquedaOferta.getSalarioAnualMinimo() != 0
                    && ofertaIteracion.getSalarioAnual() < busquedaOferta.getSalarioAnualMinimo()) {
                iterator.remove();
                continue;
            }

            if (busquedaOferta.getModalidadTrabajo() != null
                    && busquedaOferta.getModalidadTrabajo() != ofertaIteracion.getModalidadTrabajo()) {
                iterator.remove();
            }
        }
        return resultado;

    }

    @Override
    public boolean estaSuscritoOferta(Long id) {
        if (buscaService.obtenerBuscaConectado() != null
                && this.obtenerPorId(id) != null
                && buscaService.obtenerBuscaConectado().getListaOfertas().contains(this.obtenerPorId(id))) {
            return true;
        }
        return false;
    }

    @Override
    public OfertaDto convertirEntidadOfertaADto(Oferta oferta) {
        return OfertaDto.builder()
                .id(oferta.getId())
                .puesto(oferta.getPuesto())
                .sector(oferta.getSector())
                .descripcion(oferta.getDescripcion())
                .ciudad(oferta.getCiudad())
                .salarioAnual(oferta.getSalarioAnual())
                .tipoContrato(oferta.getTipoContrato())
                .horas(oferta.getHoras())
                .modalidadTrabajo(oferta.getModalidadTrabajo())
                .contrata(oferta.getContrata())
                .numeroCandidatos(
                        oferta.getListaCandidatos() != null
                                ? oferta.getListaCandidatos().size()
                                : 0)
                .build();
    }

    /* NOTA: Su relación con el usuario contrata se mapeará en otro método */

    @Override
    public Oferta convertirNuevaOfertaDtoAEntidad(OfertaDto ofertaDto) {
        return Oferta.builder()
                .id(null) // normalmente esto solo se usa en UPDATE
                .puesto(ofertaDto.getPuesto())
                .sector(ofertaDto.getSector())
                .descripcion(ofertaDto.getDescripcion())
                .ciudad(ofertaDto.getCiudad())
                .salarioAnual(ofertaDto.getSalarioAnual())
                .tipoContrato(ofertaDto.getTipoContrato())
                .horas(ofertaDto.getHoras())
                .modalidadTrabajo(ofertaDto.getModalidadTrabajo())
                .fechaPublicacion(LocalDate.now())
                .contrata(null)
                .listaCandidatos(null)
                .build();
    }


    @Override
    public void inscribirBuscaConectadoWrapper(Long id) {
        Busca buscaConectado = buscaService.obtenerBuscaConectado();
        Oferta oferta = this.obtenerPorId(id);

        oferta.getListaCandidatos().add(buscaConectado);
        buscaConectado.getListaOfertas().add(oferta);

        this.guardarOferta(oferta);
        buscaService.guardarSinEncriptar(buscaConectado);
    }

    @Override
    public void desinscribirBuscaConectadoWrapper(Long id) {
        Busca buscaConectado = buscaService.obtenerBuscaConectado();
        Oferta oferta = this.obtenerPorId(id);

        // Sospechoso de fallar
        oferta.getListaCandidatos().remove(buscaConectado);
        buscaConectado.getListaOfertas().remove(oferta);

        this.guardarOferta(oferta);
        buscaService.guardarSinEncriptar(buscaConectado);
    }

}
