package com.example.demo.services;

import java.util.List;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.usuarios.Busca;

public interface ConocimientoService {
    Conocimiento guardarConocimiento(Conocimiento c);
    Conocimiento guardarConocimientoDemoApp(Busca busca,Conocimiento c);
    void actualizarConocimiento(Conocimiento conocimiento);
    void guardarListaConocimientos(Busca busca,List <Conocimiento> conocimientos);
    Conocimiento obtenerPorId(Long id);
    void borrarConocimiento(Long id);
    void borrarTodosPorBusca(Long id);
    List<Conocimiento> obtenerTodos();
    List<Conocimiento> obtenerTodosSet();
}
