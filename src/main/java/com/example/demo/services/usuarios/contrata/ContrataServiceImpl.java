package com.example.demo.services.usuarios.contrata;

import java.util.ArrayList;
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

import com.example.demo.domain.dtos.NuevoUsuarioDto;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.OfertaDtoEmployer;
import com.example.demo.domain.ofertas.OfertaDtoJobSearch;
import com.example.demo.domain.usuarios.contrata.Contrata;
import com.example.demo.repositories.ContrataRepository;
import com.example.demo.services.ofertas.OfertaMapper;

@Service
public class ContrataServiceImpl implements ContrataService {

    @Autowired
    ContrataRepository repo;

    @Autowired
    OfertaMapper ofertaMapper;

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
    public Contrata guardarNuevoUsuarioFromDto(NuevoUsuarioDto dto) {
        // WARNING : Contraseña sin encriptar
        Contrata contrataFromDto = this.convertirNuevoUsuarioDtoAContrata(dto);
        Contrata resultado = this.guardar(contrataFromDto);

        return resultado;
    }

    @Override
    public Contrata convertirNuevoUsuarioDtoAContrata(NuevoUsuarioDto dto) {
        return new Contrata(dto.getNombre(), dto.getEmail(),
                dto.getCiudad(), dto.getTelefono(), dto.getPassword());
    }

    @Override
    public void borrarContrata(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void borrarContrataWrapper() {

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
    public boolean esNombreRepetido(String nombre) {
        if (obtenerPorNombre(nombre) != null)
            return true;
        else
            return false;
    }

    @Override
    public String obtenerNombre() {
        return obtenerContrataConectado().getNombre();
    }

    @Override
    public Contrata obtenerContrataConectado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Contrata currentUsuario = this.obtenerPorNombre(authentication.getName());
        return currentUsuario;
        // if (auth instanceof AnonymousAuthenticationToken) return null;
    }

    private final int ofertasPorPagina = 8;

    @Override
    public Page<OfertaDtoEmployer> obtenerPaginaOfertasPublicadas(Integer paginaElecta) {

        Pageable paginable = PageRequest.of(paginaElecta, ofertasPorPagina);

        // Obtienes la lista original
        List<Oferta> listaOfertas = new ArrayList<>(this.obtenerContrataConectado().getListaOfertas());

        // Cálculo de índices
        int inicio = (int) paginable.getOffset();
        int fin = Math.min(inicio + paginable.getPageSize(), listaOfertas.size());

        // Sublista solo de la página seleccionada
        List<OfertaDtoEmployer> pagina = listaOfertas.subList(inicio, fin).stream()
                .map(ofertaMapper::mapOfertaEntityToEmployerDto)
                .toList();

        // Devuelves la página final
        return new PageImpl<>(pagina, paginable, listaOfertas.size());
    }
}

// @Override
// public String generarApiKey() {
// Contrata contrata = this.obtenerContrataConectado();
// String key = UUID.randomUUID().toString();

// contrata.setApiKey(passwordEncoder.encode(key));
// this.guardarSinEncriptar(contrata);

// return key;
// }

// @Override
// public void borrarApiKey() {
// Contrata contrata = this.obtenerContrataConectado();
// contrata.setApiKey(null);
// this.guardarSinEncriptar(contrata);
// }
