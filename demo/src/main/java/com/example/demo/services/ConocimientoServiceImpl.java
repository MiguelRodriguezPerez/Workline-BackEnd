package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.dtos.ConocimientoDto;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.repositories.ConocimientoRepository;
import com.example.demo.services.usuarios.BuscaService;

@Service
public class ConocimientoServiceImpl implements ConocimientoService{
    
    @Autowired
    ConocimientoRepository repo;

    @Autowired
    BuscaService buscaService;

    @Override
    public Conocimiento guardarConocimiento(Conocimiento c) {
        return repo.save(c);
    }

    public Conocimiento guardarConocimientoDemoApp(Busca busca,Conocimiento c) {
        c.setBusca(busca);
        busca.getListaConocimientos().add(c);

        this.guardarConocimiento(c);
        buscaService.guardarSinEncriptar(busca);
        
        return c;
    }

    @Override
    public Conocimiento guardarCambios(ConocimientoDto dto, Long id){

        Conocimiento conocimiento = this.obtenerPorId(id);

        conocimiento.setCentroEducativo(dto.getCentroEducativo());
        conocimiento.setTitulo(dto.getTitulo());
        conocimiento.setInicioPeriodoConocimiento(dto.getInicioPeriodoConocimiento());
        conocimiento.setFinPeriodoConocimiento(dto.getFinPeriodoConocimiento());

        return this.guardarConocimiento(conocimiento);
    }

    @Override
    public Conocimiento guardarConocimientoFromContrata(Conocimiento conocimiento){
        Busca buscaConectado = buscaService.obtenerBuscaConectado();
        conocimiento.setBusca(buscaConectado);
        buscaConectado.getListaConocimientos().add(conocimiento);

        return this.guardarConocimiento(conocimiento);
    }

    @Override
    public Conocimiento obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void borrarConocimiento(Long id) {
        repo.deleteById(id);
    }


    @Override
    public void borrarTodosPorBusca(Long id) {
        Busca busca = buscaService.obtenerPorId(id);
        busca.setListaConocimientos(null);
        buscaService.guardarSinEncriptar(busca);

        repo.deleteAllConocimientoFromBuscaId(busca.getId());
    }

    @Override
    public List<Conocimiento> obtenerTodos() {
        return repo.findAll();
    }

    @Override
    public List<Conocimiento> obtenerTodosSet() {

        ArrayList<Conocimiento> resultado = new ArrayList<Conocimiento>();

        for(Conocimiento con: obtenerTodos()){
            resultado.add(con);
        }

        return resultado;
    }

    @Override
    public void actualizarConocimiento(Conocimiento conocimiento) {
        Conocimiento cEditar = this.obtenerPorId(conocimiento.getId());

        cEditar.setId(conocimiento.getId());
        cEditar.setBusca(buscaService.obtenerBuscaConectado());

        cEditar.setCentroEducativo(conocimiento.getCentroEducativo());
        cEditar.setTitulo(conocimiento.getTitulo());
        cEditar.setInicioPeriodoConocimiento(conocimiento.getInicioPeriodoConocimiento());
        cEditar.setFinPeriodoConocimiento(conocimiento.getFinPeriodoConocimiento());

        this.guardarConocimiento(cEditar);
    }

    @Override
    public Conocimiento convertirConocimientoDtoAConocimiento(ConocimientoDto dto) {
       return new Conocimiento(dto.getCentroEducativo(), dto.getTitulo(), 
            dto.getInicioPeriodoConocimiento(), dto.getFinPeriodoConocimiento());
    }

    
}
