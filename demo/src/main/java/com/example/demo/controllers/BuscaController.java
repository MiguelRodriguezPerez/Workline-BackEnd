package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.Experiencia;
import com.example.demo.services.usuarios.BuscaService;

@RequestMapping("/busca/api")
@RestController
public class BuscaController {
    
    @Autowired
    BuscaService buscaService;

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
}
