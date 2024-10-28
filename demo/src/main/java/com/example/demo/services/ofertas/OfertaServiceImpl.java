package com.example.demo.services.ofertas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entidadesApi.OfertaApi;
import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.exceptions.PagOfertasPublicadasIncorrecta;
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
    public Oferta guardarOfertaFromContrata(Oferta oferta) {
        Contrata contrataConectado = contrataService.obtenerContrataConectado();

        oferta.setNombreEmpresa(contrataConectado.getNombre());
        oferta.setContrata(contrataConectado);

        /*
         * Como este método sirve para guardar nuevas ofertas, pero también sirve
         * para editar las ofertas, se comprueba si la fecha es nula para evitar que en
         * caso
         * de que se edite una oferta la fecha no cambie
         */
        if (oferta.getFechaPublicacion() == null)
            oferta.setFechaPublicacion(LocalDate.now());

        this.guardarOferta(oferta);
        contrataConectado.getListaOfertas().add(oferta);
        contrataService.guardarSinEncriptar(contrataConectado);

        return oferta;
    }

    @Override
    public Oferta guardarCambios(Oferta oferta) {
        Oferta ofertaEdit = this.obtenerPorId(oferta.getId());

        ofertaEdit.setPuesto(oferta.getPuesto());
        ofertaEdit.setSector(oferta.getSector());
        ofertaEdit.setModalidadTrabajo(oferta.getModalidadTrabajo());
        ofertaEdit.setDescripcion(oferta.getDescripcion());
        ofertaEdit.setSalarioAnual(oferta.getSalarioAnual());
        ofertaEdit.setTipoContrato(oferta.getTipoContrato());
        ofertaEdit.setHoras(oferta.getHoras());
        ofertaEdit.setFechaPublicacion(oferta.getFechaPublicacion());
        ofertaEdit.setCiudad(oferta.getCiudad());

        return this.guardarOferta(ofertaEdit);
    }

    @Override
    public void borrarOferta(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void borrarCandidatosOferta(Long id) {
        Oferta oferta = this.obtenerPorId(id);
        ArrayList<Busca> listaCandidatos = new ArrayList<>(oferta.getListaCandidatos());
        for (Busca b : listaCandidatos) {
            b.getListaOfertas().remove(oferta);
            buscaService.guardarSinEncriptar(b);

            oferta.getListaCandidatos().remove(b);
            this.guardarOferta(oferta);
        }
    }

    @Override
    public void borrarBuscaTodasOfertas(Busca busca) {
        repo.borrarBuscaFromAllOfertas(busca.getId());
    }

    @Override
    public void borrarContrataTodasOfertas(Contrata contrata) {
        repo.borrarCandidatosTodasOfertas(contrata.getId());
    }

    @Override
    public Oferta obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Oferta> obtenerTodos() {
        ArrayList<Oferta> resultado = (ArrayList<Oferta>) repo.findAll();
        Collections.sort(resultado, (f1, f2) -> f1.getFechaPublicacion().compareTo(f2.getFechaPublicacion()));
        return resultado;
    }



    private final Integer ofertasPorPagina = 10;

    @Override
    public Page<Oferta> obtenerPaginaApi(int numPag, BusquedaOferta busquedaOferta) {
        List<Oferta> resultadosBusqueda = this.obtenerResultados(busquedaOferta);
        if (resultadosBusqueda.isEmpty()) return null;

        Pageable paginable = PageRequest.of(numPag, ofertasPorPagina);
        int primeraOferta = (int) paginable.getOffset();
        int ultimaOferta = Math.min(primeraOferta + paginable.getPageSize(), resultadosBusqueda.size());

        Page<Oferta> resultado = new PageImpl<>(resultadosBusqueda.subList(primeraOferta, ultimaOferta), paginable,
                resultadosBusqueda.size());
        return resultado;
    }

    @Override
    public List<Oferta> obtenerResultados(BusquedaOferta busquedaOferta) {
        System.out.println(busquedaOferta + "AAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(busquedaOferta.getPuestoB());

        List<Oferta> resultado = this.obtenerTodos();

        // Use an iterator to avoid ConcurrentModificationException
        Iterator<Oferta> iterator = resultado.iterator();

        while (iterator.hasNext()) {
            Oferta ofertaIteracion = iterator.next();
    
            if (!busquedaOferta.getPuestoB().equalsIgnoreCase("")
                && !busquedaOferta.getPuestoB().equalsIgnoreCase(ofertaIteracion.getPuesto())) {
                iterator.remove();
                continue;
            }
    
            if (!busquedaOferta.getSectorB().equalsIgnoreCase("") 
                && !busquedaOferta.getSectorB().equalsIgnoreCase(ofertaIteracion.getSector())) {
                iterator.remove();
                continue;
            }
    
            if (!busquedaOferta.getTipoContratoB().equalsIgnoreCase("")
                && !busquedaOferta.getTipoContratoB().equalsIgnoreCase(ofertaIteracion.getTipoContrato().toString())) {
                iterator.remove();
                continue;
            }
    
            if (!busquedaOferta.getCiudadB().equalsIgnoreCase("")
                && !busquedaOferta.getCiudadB().equalsIgnoreCase(ofertaIteracion.getCiudad())) {
                iterator.remove();
                continue;
            }
    
            if (busquedaOferta.getSalarioAnualMinimo() != 0 
                && ofertaIteracion.getSalarioAnual() < busquedaOferta.getSalarioAnualMinimo()) {
                iterator.remove();
                continue;
            }
    
            if (!busquedaOferta.getModalidadB().equalsIgnoreCase("")
                && !busquedaOferta.getModalidadB().equalsIgnoreCase(ofertaIteracion.getModalidadTrabajo().toString())) {
                iterator.remove();
            }
        }
        return resultado;
        
    }

   
    @Override
    public void cambiarPropiedadOfertas(List<Oferta> listaOfertas, String username) {
        for (Oferta oferta : listaOfertas) {
            oferta.setNombreEmpresa(username);
            this.guardarCambios(oferta);
        }
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

}
