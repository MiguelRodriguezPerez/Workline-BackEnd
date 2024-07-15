package com.example.demo.services.ofertas;

import java.util.Comparator;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.repositories.OfertaRepository;

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
    public TreeMap<Long, Oferta> obtenerTodos() {
        TreeMap<Long,Oferta> resultado = new TreeMap<>();
        Comparator<Oferta> comparator = (o1,o2) -> o1.getFechaPublicacion().compareTo(o2.getFechaPublicacion());

        for(Oferta oferta: repo.findAll()){
            resultado.put(oferta.getId(),oferta);
        }
    }

    @Override
    public TreeMap<Long, Oferta> obtenerResultados(BusquedaOferta busquedaOferta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerResultados'");
    }

    @Override
    public TreeMap<Long, Oferta> obtenerPagina(Integer paginaElecta, BusquedaOferta busquedaOferta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPagina'");
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
