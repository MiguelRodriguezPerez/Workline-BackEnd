package com.example.demo.services.usuarios;

import java.util.List;

import com.example.demo.domain.userRelated.Busca;

public interface BuscaService {
    
    Busca guardar(Busca busca);
    // Busca guardarFromUsuario(Usuario usuario);
    Busca guardarSinEncriptar(Busca busca);
    void borrar(Long id);
    List <Busca> obtenerTodos();
    Busca obtenerPorId(Long id);
    Busca obtenerPorNombre(String nombre);
    String obtenerNombre();
    Busca obtenerBuscaConectado();
    boolean estaSuscritoOferta(Long id);
}
