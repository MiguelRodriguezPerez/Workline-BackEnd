package com.example.demo.services.usuarios;

import java.util.List;

import com.example.demo.domain.dtos.NuevoUsuarioDto;
import com.example.demo.domain.modelView.BuscaView;
import com.example.demo.domain.usuarios.Busca;

public interface BuscaService {

    Busca guardar(Busca busca);
    Busca guardarSinEncriptar(Busca busca);
    Busca guardarCambios(Busca busca);
    Busca guardarNuevoUsuarioFromDto(NuevoUsuarioDto dto);
    void borrar(Long id);
    void borrarCuentaWrapper();
    List<Busca> obtenerTodos();
    Busca obtenerPorId(Long id);
    Busca obtenerPorNombre(String nombre);
    boolean esNombreRepetido(String nombre);
    Busca obtenerBuscaConectado();
    boolean coincidePassword(String verificarPassword);
    void cambiarPassword(String nuevoPassword);
    Boolean estaInscritoOferta(Long id);
    BuscaView convertirBuscaABuscaView(Busca busca);
    Busca convertirNuevoUsuarioDtoABusca(NuevoUsuarioDto dto);

}
