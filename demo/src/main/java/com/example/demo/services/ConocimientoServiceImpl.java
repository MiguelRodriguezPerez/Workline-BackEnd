package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Conocimiento;
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
    public void guardarListaConocimientos(Busca busca, List<Conocimiento> conocimientos) {

        busca.setListaConocimientos(conocimientos);
        buscaService.guardarSinEncriptar(busca);
       
        for(Conocimiento con: conocimientos){

            con.setBusca(busca);
            guardarConocimiento(con);
            
        }
    }

    @Override
    public Conocimiento obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void borrarConocimiento(Long id) {
        repo.delete(this.obtenerPorId(id));
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
}
