package com.example.demo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Experiencia;
import com.example.demo.domain.dtos.usuarios.busca.ExperienciaDto;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.repositories.ExperienciaRepository;
import com.example.demo.services.usuarios.BuscaService;

@Service
public class ExperienciaServiceImpl implements ExperienciaService{

    @Autowired
    ExperienciaRepository repo;

    @Autowired
    BuscaService buscaService;

    @Override
    public Experiencia guardarExperiencia(Experiencia ex) {
        return repo.save(ex);
    }

    @Override
    public Experiencia guardarExperienciaDemoApp(Busca busca, Experiencia experiencia){
        experiencia.setBusca(busca);
        busca.getListaExperiencias().add(experiencia);

        buscaService.guardarSinEncriptar(busca);
        this.guardarExperiencia(experiencia);

        return experiencia;
    }

    @Override
    public Experiencia guardarCambios(ExperienciaDto experienciaDto, Long id){
        Experiencia exp = this.obtenerPorId(id);

        exp.setPuesto(experienciaDto.getPuesto());
        exp.setEmpresa(experienciaDto.getEmpresa());
        exp.setInicioExperiencia(experienciaDto.getInicioExperiencia());
        exp.setFinExperiencia(experienciaDto.getFinExperiencia());

        return this.guardarExperiencia(exp);
    }

    @Override
    public Experiencia guardarExperienciaFromBusca(Experiencia experiencia){

        Busca busca = buscaService.obtenerBuscaConectado();

        experiencia.setBusca(busca);
        busca.getListaExperiencias().add(experiencia);

        this.guardarExperiencia(experiencia);
        buscaService.guardarSinEncriptar(busca);

        return experiencia;
    }

    @Override
    public Experiencia obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void borrarExperiencia(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void borrarExperienciaWrapper(Long id){
        Busca buscaConectado = buscaService.obtenerBuscaConectado();
        buscaConectado.getListaExperiencias().removeIf(experiencia -> experiencia.getId() == id);
        buscaService.guardarSinEncriptar(buscaConectado);

        /*No llamas a this.borrarExperiencia porque la entidad experiencia esta configurada
        para que se borre si queda huérfana*/
    }

    @Override
    public void borrarTodosPorBusca(Long id) {
        Busca busca = buscaService.obtenerPorId(id);
        
        busca.setListaExperiencias(null);
        buscaService.guardarSinEncriptar(busca);

        repo.deleteAllExperienciaByBuscaId(busca.getId());
    }

    @Override
    public List<Experiencia> obtenerTodos() {
        return repo.findAll();
    }

    @Override
    public Set<Experiencia> obtenerTodosSet() {
        
        Set<Experiencia> resultado = new HashSet<>();
        for(Experiencia exp: obtenerTodos()){
            resultado.add(exp);
        }
        return resultado;
    }

    @Override
    public Experiencia convertirExperienciaDtoAExperiencia(ExperienciaDto dto) {
        return new Experiencia(null, 
            dto.getPuesto(), dto.getEmpresa(), 
            dto.getInicioExperiencia(), dto.getFinExperiencia(), null);
    }

}
