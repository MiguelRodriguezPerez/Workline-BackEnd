package com.example.demo.services.ofertas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.repositories.OfertaRepository;

@Service
public class OfertaServiceImpl implements OfertaService{

    @Autowired
    OfertaRepository repo;

    @Override
    public Oferta guardarOferta(Oferta oferta) {
        return repo.save(oferta);
    }

    @Override
    public void borrarOferta(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Oferta obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Oferta> obtenerTodos() {
        ArrayList<Oferta> resultado = (ArrayList<Oferta>)repo.findAll();
        Collections.sort(resultado,(f1,f2) -> f1.getFechaPublicacion().compareTo(f2.getFechaPublicacion()));
        return resultado;
    }

    @Override
    public boolean coincidenEstudios(Oferta oferta, BusquedaOferta busquedaOferta) {
       for(String requisito: oferta.getRequisitos()){
            if(!busquedaOferta.getRequisitos().contains(requisito)) return false;
       }
       return true;
    }

    @Override
    public List<Oferta> obtenerResultados(BusquedaOferta busquedaOferta) {
        ArrayList<Oferta> listaResultado = new ArrayList<>();

        listaResultado.removeIf(o -> 
        busquedaOferta.getPuestoB() != null
        && !busquedaOferta.getPuestoB().equals("")
        && !o.getPuesto().equals(busquedaOferta.getPuestoB()));

        listaResultado.removeIf(o -> 
        busquedaOferta.getSectorB() != null
        && !busquedaOferta.getSectorB().equals("placeholder")
        && !busquedaOferta.getSectorB().equals("")
        && !o.getSector().equals(busquedaOferta.getSectorB()));

        listaResultado.removeIf(o -> 
        busquedaOferta.getTipoContratoB() != null 
        && busquedaOferta.getTipoContratoB().equals("")
        && !o.getModalidadTrabajo().toString().equalsIgnoreCase(busquedaOferta.getTipoContratoB()));//PELIGRO COMPARANDO ENUMS

        listaResultado.removeIf(o -> 
        busquedaOferta.getCiudadB() != null 
        && busquedaOferta.getCiudadB().equals("")
        && !o.getCiudad().equals(busquedaOferta.getCiudadB()));

        listaResultado.removeIf(o -> 
        o.getSalarioAnual() != null
        && o.getSalarioAnual() < busquedaOferta.getSalarioAnual());

        listaResultado.removeIf(o -> 
        busquedaOferta.getModalidadB()!= null
        && !o.getModalidadTrabajo().toString().equalsIgnoreCase(busquedaOferta.getModalidadB()));//PELIGRO COMPARANDO ENUMS

        //Comprobar si los estudios requeridos coinciden
        if(!busquedaOferta.getRequisitos().isEmpty() || busquedaOferta.getRequisitos() != null){
            for(Oferta oferta: listaResultado){//Sospechoso de fallar
                if(!coincidenEstudios(oferta, busquedaOferta))listaResultado.remove(oferta);
            }
        }

        Collections.sort(listaResultado,(f1,f2) -> f1.getFechaPublicacion().compareTo(f2.getFechaPublicacion()));
        
        return listaResultado;
    }

    private final Integer ofertasPorPagina = 10;

    @Override
    public List<Oferta> obtenerPagina(Integer numeroPag, BusquedaOferta busquedaOferta) {
        Pageable paginable = PageRequest.of(numeroPag,ofertasPorPagina);
        

        if(busquedaOferta == null){
            Page<Oferta> resultado = repo.findAll(paginable);
            return resultado.getContent();
        }
        else{
            List<Oferta> listaOfertas = obtenerResultados(busquedaOferta);
            if(listaOfertas.isEmpty()) return null;

            int primeraOferta = (int) paginable.getOffset();
            int ultimaOferta = Math.min(primeraOferta + paginable.getPageSize(),listaOfertas.size());

            Page<Oferta> resultado = new PageImpl<>(listaOfertas.subList(primeraOferta, ultimaOferta),paginable,listaOfertas.size());

            // if(resultado.hasContent()) 
            return resultado.getContent();

        }
    }

    @Override
    public boolean existeSiguientePagina(Integer paginaElecta, BusquedaOferta busquedaOferta) {
        if(obtenerNumeroPaginas(busquedaOferta) > paginaElecta) return true;
        else return false;
    }

    @Override
    public int existeAnteriorPagina(Integer paginaElecta) {
        if(paginaElecta>0){
            paginaElecta--;
            return paginaElecta;
        }
        else return paginaElecta;
    }

    @Override
    public int obtenerNumeroPaginas(BusquedaOferta busquedaOferta) {
        if(busquedaOferta == null){
            Pageable paginable = PageRequest.of(0,ofertasPorPagina);
            Page<Oferta> resultado = repo.findAll(paginable);

            return resultado.getTotalPages();
        }

        Pageable paginable = PageRequest.of(0,ofertasPorPagina);
        List<Oferta> listaOfertas = obtenerResultados(busquedaOferta);

        int primeraOferta = (int) paginable.getOffset();
        int ultimaOferta = Math.min(primeraOferta + paginable.getPageSize(),listaOfertas.size());

        Page<Oferta> resultado = new PageImpl<>(listaOfertas.subList(primeraOferta, ultimaOferta),paginable,listaOfertas.size());

        return resultado.getTotalPages();
    }

    @Override
    public void cambiarPropiedadOfertas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cambiarPropiedadOfertas'");
    }
    
    
}
