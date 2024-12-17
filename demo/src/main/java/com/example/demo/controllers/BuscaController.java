package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.Experiencia;
import com.example.demo.domain.dtos.ConocimientoDto;
import com.example.demo.domain.dtos.ExperienciaDto;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.services.ConocimientoService;
import com.example.demo.services.ExperienciaService;
import com.example.demo.services.usuarios.BuscaService;

@RequestMapping("/busca/api")
@RestController
public class BuscaController {
    
    @Autowired
    BuscaService buscaService;

    @Autowired
    ExperienciaService experienciaService;

    @Autowired
    ConocimientoService conocimientoService;

    @GetMapping("/misExperiencias")
    public ResponseEntity<List<Experiencia>> getMyExperiencias(){
        List<Experiencia> resultado = buscaService.obtenerBuscaConectado().getListaExperiencias();
        if(resultado.isEmpty() || resultado == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping("/nuevaExperiencia")
    public ResponseEntity<Experiencia> submitNewExperiencia(@RequestBody ExperienciaDto dto){
        Experiencia experiencia = experienciaService.convertirExperienciaDtoAExperiencia(dto);
        experienciaService.guardarExperienciaFromBusca(experiencia);
        return new ResponseEntity<>(experiencia,HttpStatus.CREATED);
    }

    @PutMapping("/editarExperiencia/{id}")
    public ResponseEntity<Experiencia> editExperienciaEndpoint(@RequestBody ExperienciaDto expRequest,
        @PathVariable Long id){
        Experiencia experiencia = experienciaService.guardarCambios(expRequest, id);
        return new ResponseEntity<>(experiencia, HttpStatus.CREATED);
    }

    @DeleteMapping("/borrarExperiencia/{id}")
    public ResponseEntity<Void> deleteExperienciaEndpoint(@PathVariable Long id){
        experienciaService.borrarExperiencia(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/misConocimientos")
    public ResponseEntity<List<Conocimiento>> getMyConocimientos(){
        List<Conocimiento> resultado = buscaService.obtenerBuscaConectado().getListaConocimientos();
        if(resultado.isEmpty() || resultado == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
    
    @PostMapping("/nuevoConocimiento")
    public ResponseEntity<Conocimiento> submitNewConocimiento(@RequestBody ConocimientoDto conocimientoDto){
        Conocimiento conocimiento = conocimientoService.convertirConocimientoDtoAConocimiento(conocimientoDto);
        Conocimiento resultado = conocimientoService.guardarConocimientoFromContrata(conocimiento);

        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @PutMapping("/editarConocimiento/{id}")
    public ResponseEntity<Conocimiento> editConocimientoEndpoint(@RequestBody ConocimientoDto conocimientoDto,
        @PathVariable Long id){
        Conocimiento resultado = conocimientoService.guardarCambios(conocimientoDto, id);
        return new ResponseEntity<>(resultado,HttpStatus.CREATED);
    }

    @DeleteMapping("/borrarConocimiento/{id}")
    public ResponseEntity<Void> deleteConocimientoEndpoint(@PathVariable Long id){
        conocimientoService.borrarConocimiento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/estaInscritoOferta/{id}")
    public ResponseEntity<Boolean> getListOfertaId(@PathVariable Long id){
        return new ResponseEntity<>(buscaService.estaInscritoOferta(id),HttpStatus.OK);
    }

    @GetMapping("/miListaOfertas")
    public ResponseEntity<List<Oferta>> getMyListOfertas(){
        List<Oferta> resultado = buscaService.obtenerBuscaConectado().getListaOfertas();
        if(resultado.size() == 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
}
