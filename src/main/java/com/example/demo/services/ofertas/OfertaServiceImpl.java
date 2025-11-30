package com.example.demo.services.ofertas;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.OfertaDto;
import com.example.demo.domain.usuarios.busca.Busca;
import com.example.demo.domain.usuarios.contrata.Contrata;
import com.example.demo.repositories.OfertaRepository;
import com.example.demo.services.usuarios.busca.BuscaService;
import com.example.demo.services.usuarios.contrata.ContrataService;

@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    OfertaRepository repo;

    @Autowired
    OfertaMapper ofertaMapper;

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
        Oferta oferta = ofertaMapper.mapNewOfertaDtoToEntity(ofertaDto);
        Contrata contrata = contrataService.obtenerContrataConectado();

        oferta.setContrata(contrata);
        contrata.getListaOfertas().add(oferta);

        this.guardarOferta(oferta);
        contrataService.guardarSinEncriptar(contrata);

        return ofertaMapper.mapOfertaEntityToDto(oferta);
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

        Oferta resultado = this.guardarOferta(oferta);

        return ofertaMapper.mapOfertaEntityToDto(resultado);
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
        List<Oferta> ofertasFiltradas = this.obtenerResultados(busquedaOferta);

        Pageable paginable = PageRequest.of(numPag, ofertasPorPagina);
        int primera = (int) paginable.getOffset();
        int ultima = Math.min(primera + paginable.getPageSize(), ofertasFiltradas.size());

        List<Oferta> paginaSinMapear = ofertasFiltradas.subList(primera, ultima);

        List<OfertaDto> paginaDto = paginaSinMapear.stream()
                .map(ofertaMapper::mapOfertaEntityToDto)
                .toList();

        return new PageImpl<>(paginaDto, paginable, ofertasFiltradas.size());
    }


    @Override
    public List<Oferta> obtenerResultados(BusquedaOferta busquedaOferta) {
        return this.obtenerTodos().stream()
                .filter(oferta -> busquedaOferta.getPuesto() == null 
                        || busquedaOferta.getPuesto().isBlank()
                        || busquedaOferta.getPuesto().equalsIgnoreCase(oferta.getPuesto()))
                .filter(oferta -> busquedaOferta.getCiudad() == null 
                        || busquedaOferta.getCiudad().isBlank()
                        || busquedaOferta.getCiudad().equalsIgnoreCase(oferta.getCiudad()))
                .filter(oferta -> busquedaOferta.getSalarioAnualMinimo() == null 
                        || busquedaOferta.getSalarioAnualMinimo() == 0
                        || oferta.getSalarioAnual() >= busquedaOferta.getSalarioAnualMinimo())
                .filter(oferta -> busquedaOferta.getModalidadTrabajo() == null 
                        || busquedaOferta.getModalidadTrabajo() == oferta.getModalidadTrabajo())
                .filter(oferta -> busquedaOferta.getTipoContrato() == null 
                        || busquedaOferta.getTipoContrato() == oferta.getTipoContrato())
                .toList(); 
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



    /* NOTA: Su relación con el usuario contrata se mapeará en otro método */



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
