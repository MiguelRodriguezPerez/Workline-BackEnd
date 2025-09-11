package com.example.demo.services.usuarios;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.domain.dtos.NuevoUsuarioDto;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Contrata;

public interface ContrataService {
    
    Contrata guardar(Contrata contrata);
    Contrata guardarSinEncriptar(Contrata contrata);
    Contrata guardarNuevoUsuarioFromDto(NuevoUsuarioDto dto);
    Contrata convertirNuevoUsuarioDtoAContrata(NuevoUsuarioDto dto);
    void borrarContrata(Long id);
    void borrarContrataWrapper();
    List<Contrata> obtenerTodos();
    Contrata obtenerPorId(Long id);
    Contrata obtenerPorNombre(String nombre);
    boolean esNombreRepetido(String nombre);
    String obtenerNombre();
    Contrata obtenerContrataConectado();
    Page<Oferta> obtenerPaginaOfertasPublicadas(Integer paginaElecta);
    // String generarApiKey();
    // void borrarApiKey();
    
}
