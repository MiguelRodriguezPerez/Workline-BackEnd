package com.example.demo.services;

import java.util.List;
import java.util.Set;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.usuarios.Busca;

public interface ConocimientoService {
    Conocimiento guardarConocimiento(Conocimiento c);
    void actualizarConocimiento(Conocimiento conocimiento);
    void guardarListaConocimientos(Busca busca,Set <Conocimiento> conocimientos);
    Conocimiento obtenerPorId(Long id);
    void borrarConocimiento(Long id);
    List<Conocimiento> obtenerTodos();
    Set<Conocimiento> obtenerTodosSet();
}
