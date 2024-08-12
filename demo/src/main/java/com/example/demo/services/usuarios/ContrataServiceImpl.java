package com.example.demo.services.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.NuevoUsuario;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.exceptions.PagContrataIncorrectaException;
import com.example.demo.repositories.ContrataRepository;

@Service
public class ContrataServiceImpl implements ContrataService{
    
    @Autowired
    ContrataRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Contrata guardar(Contrata contrata) {
        contrata.setPassword(passwordEncoder.encode(contrata.getPassword()));
        return repo.save(contrata);
    }

    @Override
    public Contrata guardarSinEncriptar(Contrata contrata) {
        return repo.save(contrata);
    }

    @Override
    public Contrata guardarContrataDesdeNuevoUsuario(NuevoUsuario nuevoUsuario) {
        Contrata contrata = new Contrata(nuevoUsuario.getNombre(), nuevoUsuario.getEmail()
        , nuevoUsuario.getCiudad(), nuevoUsuario.getTelefono(), nuevoUsuario.getPassword());
        contrata.setPassword(passwordEncoder.encode(contrata.getPassword()));

        return repo.save(contrata);
    }

    @Override
    public Contrata guardarCambios(Contrata contrata) {
        contrata.setListaOfertas(this.obtenerContrataConectado().getListaOfertas());
        if(!contrata.getPassword().equals(this.obtenerContrataConectado().getPassword())) return this.guardar(contrata);
        else return this.guardarSinEncriptar(contrata);
    }

    @Override
    public void borrarContrata(Long id) {
       repo.deleteById(id);
    }

    @Override
    public List<Contrata> obtenerTodos() {
        return repo.findAll();
    }

    @Override
    public Contrata obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Contrata obtenerPorNombre(String nombre) {
       return repo.findByNombre(nombre);
    }
    
    @Override
    public boolean esNombreRepetido(String nombre){
        if(obtenerPorNombre(nombre) != null) return true;
        else return false;
    }

    @Override
    public String obtenerNombre() {
       return obtenerContrataConectado().getNombre();
    }

    @Override
    public Contrata obtenerContrataConectado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Contrata contrata = obtenerPorNombre(auth.getName());
        return contrata;
    }

    private final int ofertasPorPagina = 8;

    @Override
    public List<Oferta> obtenerPaginaOfertasPublicadas(Integer paginaElecta) {
        // if(paginaElecta < 0 || paginaElecta > this.obtenerNumeroPaginasOfertasPublicadas()){
        //     throw new PagContrataIncorrectaException();
        // }

        Pageable paginable = PageRequest.of(paginaElecta,ofertasPorPagina);
        List<Oferta> listaOfertas = obtenerContrataConectado().getListaOfertas();

        int inicio = (int) paginable.getOffset();
        int fin = Math.min(inicio + paginable.getPageSize(),listaOfertas.size());

        Page<Oferta> resultado = new PageImpl<>(listaOfertas.subList(inicio, fin), paginable, listaOfertas.size());

        return resultado.getContent();
    }

    @Override
    public int existeSiguientePagina(Integer paginaElecta) {
        if(paginaElecta < obtenerNumeroPaginasOfertasPublicadas() + 2){
            paginaElecta++;
            return paginaElecta;
        }
        else return paginaElecta;
    }

    @Override
    public int existeAnteriorPagina(Integer paginaElecta) {
        if(paginaElecta > 0){
            paginaElecta--;
            return paginaElecta;
        }
        else return paginaElecta;
    }

    @Override
    public int obtenerNumeroPaginasOfertasPublicadas() {
        Pageable paginable = PageRequest.of(0,ofertasPorPagina);
        Page<Oferta> resultado = new PageImpl<>(obtenerContrataConectado().getListaOfertas(), paginable, ofertasPorPagina);
        
        return resultado.getTotalPages();
    }


}
