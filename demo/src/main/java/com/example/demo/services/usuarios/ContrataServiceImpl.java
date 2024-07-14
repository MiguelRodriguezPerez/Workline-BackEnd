package com.example.demo.services.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Oferta;
import com.example.demo.domain.userRelated.Contrata;
import com.example.demo.repositories.ContrataRepository;

@Service
public class ContrataServiceImpl implements ContrataService{
    
    @Autowired
    ContrataRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Contrata guardarContrata(Contrata contrata) {
        passwordEncoder.encode(contrata.getPassword());
        return repo.save(contrata);
    }

    @Override
    public Contrata guardarContrataSinEncriptar(Contrata contrata) {
        return repo.save(contrata);
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
    public String obtenerNombre() {
       return obtenerContrataConectado().getNombre();
    }

    @Override
    public Contrata obtenerContrataConectado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Contrata contrata = obtenerPorNombre(auth.getName());
        return contrata;
    }

    @Override
    public List<Oferta> obtenerPaginaOfertasPublicadas(Integer paginaElecta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPaginaOfertasPublicadas'");
    }

    @Override
    public int siguientePaginaOfertasPublicadas(Integer pagina) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'siguientePaginaOfertasPublicadas'");
    }

    @Override
    public int anteriorPaginaOfertasPublicadas(Integer pagina) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'anteriorPaginaOfertasPublicadas'");
    }

    @Override
    public int obtenerNumeroPaginasOfertasPublicadas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerNumeroPaginasOfertasPublicadas'");
    }


}
