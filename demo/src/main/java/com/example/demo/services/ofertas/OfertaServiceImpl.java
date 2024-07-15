package com.example.demo.services.ofertas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.repositories.OfertaRepository;

public class OfertaServiceImpl implements OfertaService{

    @Autowired
    OfertaRepository repo;

    @Autowired
    BusquedaOfertaService busquedaOfertaService;

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

        TreeMap<Long,Oferta> resultado = new TreeMap<>();
        for(Oferta oferta: listaResultado){
            resultado.put(oferta.getId(), oferta);
        }
        
        return resultado;
    }

    private final Integer ofertasPorPagina = 10;

    @Override
    public TreeMap<Long, Oferta> obtenerPagina(Integer numeroPag, BusquedaOferta busquedaOferta) {
        Pageable paginable = PageRequest.of(numeroPag,ofertasPorPagina);

        if(busquedaOferta == null){
            Page
        }
    }

    @Override
    public int existeSiguientePagina(Integer paginaElecta, BusquedaOferta busquedaOferta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existeSiguientePagina'");
    }

    @Override
    public int existeAnteriorPagina(Integer paginaElecta, BusquedaOferta busquedaOferta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existeAnteriorPagina'");
    }

    @Override
    public int obtenerNumeroPaginas(BusquedaOferta busquedaOferta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerNumeroPaginas'");
    }

    @Override
    public void cambiarPropiedadOfertas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cambiarPropiedadOfertas'");
    }

    
    
}
