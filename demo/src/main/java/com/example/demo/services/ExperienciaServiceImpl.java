package com.example.demo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Experiencia;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.repositories.ExperienciaRepository;
import com.example.demo.services.usuarios.BuscaService;

@Service
public class ExperienciaServiceImpl implements ExperienciaService{

    @Autowired
    ExperienciaRepository experienciaRepository;
    @Autowired
    BuscaService buscaService;

    @Override
    public Experiencia guardarExperiencia(Experiencia ex) {
        return experienciaRepository.save(ex);
    }

    @Override
    public void guardarListaExperiencias(Busca busca, Set<Experiencia> experiencias) {
        
        busca.setListaExperiencias(experiencias);
        buscaService.guardarSinEncriptar(busca);

        for(Experiencia exp: experiencias){

            exp.setBusca(busca);
            exp.setNombreBusca(busca.getNombre());
            guardarExperiencia(exp);

        }

    }

    @Override
    public Experiencia obtenerExperienciaPorId(Long id) {
        return experienciaRepository.findById(id).orElse(null);
    }

    @Override
    public void borrarExperiencia(Long id) {
        experienciaRepository.delete(experienciaRepository.findById(id).orElse(null));
    }

    @Override
    public List<Experiencia> obtenerTodos() {
        return experienciaRepository.findAll();
    }

    @Override
    public Set<Experiencia> obtenerTodosSet() {
        
        Set<Experiencia> resultado = new HashSet<>();
        for(Experiencia exp: obtenerTodos()){
            resultado.add(exp);
        }
        return resultado;
    }
}
