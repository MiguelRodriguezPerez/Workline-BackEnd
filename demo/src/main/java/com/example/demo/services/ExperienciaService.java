package com.example.demo.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Experiencia;
import com.example.demo.domain.dtos.usuarios.busca.ExperienciaDto;
import com.example.demo.domain.usuarios.Busca;

@Service
public interface ExperienciaService  {

    Experiencia guardarExperiencia(Experiencia ex);
    Experiencia guardarExperienciaDemoApp(Busca busca, Experiencia exp);
    Experiencia guardarCambios(ExperienciaDto experienciaDto, Long id);
    Experiencia guardarExperienciaFromBusca(Experiencia experiencia);
    Experiencia obtenerPorId(Long id);
    void borrarExperiencia(Long id);
    void borrarExperienciaWrapper(Long id);
    void borrarTodosPorBusca(Long id);
    List<Experiencia> obtenerTodos();
    Set<Experiencia> obtenerTodosSet();
    Experiencia convertirExperienciaDtoAExperiencia(ExperienciaDto dto);
}
