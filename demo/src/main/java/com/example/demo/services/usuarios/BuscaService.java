package com.example.demo.services.usuarios;

import java.util.List;
import java.util.Set;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.Experiencia;
import com.example.demo.domain.dtos.NuevoUsuarioDto;
import com.example.demo.domain.modelView.BuscaView;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Busca;

public interface BuscaService {

    Busca guardar(Busca busca);
    Busca guardarSinEncriptar(Busca busca);
    Busca guardarNuevoUsuarioFromDto(NuevoUsuarioDto dto);

    void borrar(Long id);
    void borrarCuentaWrapper();

    List<Busca> obtenerTodos();
    Busca obtenerPorId(Long id);
    Busca obtenerPorNombre(String nombre);
    boolean esNombreRepetido(String nombre);

    Busca obtenerBuscaConectado();
    Set<Experiencia> obtenerExperienciasBuscaConectado();
    Set<Conocimiento> obtenerConocimientosBuscaConectado();
    Set<Oferta> obtenerOfertasBuscaConectado();

    Boolean estaInscritoOferta(Long id);
    BuscaView convertirBuscaABuscaView(Busca busca);
    Busca convertirNuevoUsuarioDtoABusca(NuevoUsuarioDto dto);

}
