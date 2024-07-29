package com.example.demo.services.usuarios;

import java.util.List;

import com.example.demo.domain.NuevoUsuario;
import com.example.demo.domain.usuarios.Busca;

public interface BuscaService {
    
    Busca guardar(Busca busca);
    Busca guardarSinEncriptar(Busca busca);
    Busca guardarBuscaDesdeNuevoUsuario(NuevoUsuario nuevoUsuario);
    void borrar(Long id);
    List <Busca> obtenerTodos();
    Busca obtenerPorId(Long id);
    Busca obtenerPorNombre(String nombre);
    boolean esNombreRepetido(String nombre);
    String obtenerNombre();
    Busca obtenerBuscaConectado();
    boolean estaSuscritoOferta(Long id);
}
