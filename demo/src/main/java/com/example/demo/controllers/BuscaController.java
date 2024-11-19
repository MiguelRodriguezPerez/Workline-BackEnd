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
import com.example.demo.domain.dtos.ExperienciaDto;
import com.example.demo.services.ExperienciaService;
import com.example.demo.services.usuarios.BuscaService;

@RequestMapping("/busca/api")
@RestController
public class BuscaController {
    
    @Autowired
    BuscaService buscaService;

    @Autowired
    ExperienciaService experienciaService;

    @GetMapping("/misConocimientos")
    public ResponseEntity<List<Conocimiento>> getMyConocimientos(){
        List<Conocimiento> resultado = buscaService.obtenerBuscaConectado().getListaConocimientos();
        if(resultado.isEmpty() || resultado == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

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
        experienciaService.borrarExperienciaWrapper(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
