package com.example.demo.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Experiencia;
import com.example.demo.domain.usuarios.Busca;

@Service
public interface ExperienciaService  {
    Experiencia guardarExperiencia(Experiencia ex);
    void guardarListaExperiencias(Busca busca,Set<Experiencia> experiencias);
    Experiencia obtenerPorId(Long id);
    void borrarExperiencia(Long id);
    List<Experiencia> obtenerTodos();
    Set<Experiencia> obtenerTodosSet();
}
