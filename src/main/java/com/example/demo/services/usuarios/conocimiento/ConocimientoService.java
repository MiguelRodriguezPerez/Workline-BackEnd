package com.example.demo.services.usuarios.conocimiento;

import java.util.List;

import com.example.demo.domain.usuarios.busca.Busca;
import com.example.demo.domain.usuarios.busca.conocimiento.Conocimiento;
import com.example.demo.domain.usuarios.busca.conocimiento.ConocimientoDto;

public interface ConocimientoService {

    Conocimiento guardarConocimiento(Conocimiento c);

    Conocimiento guardarConocimientoDemoApp(Busca busca, Conocimiento c);

    Conocimiento guardarCambios(ConocimientoDto conocimientoDto);

    Conocimiento guardarConocimientoFromBusca(Conocimiento conocimiento);

    void actualizarConocimiento(Conocimiento conocimiento);

    Conocimiento obtenerPorId(Long id);

    void borrarConocimiento(Long id);

    void borrarTodosPorBusca(Long id);

    List<Conocimiento> obtenerTodos();

    List<Conocimiento> obtenerTodosSet();

}
