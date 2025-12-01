package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entidadesApi.PaginaJobSearchRequest;
import com.example.demo.domain.ofertas.OfertaDtoEmployer;
import com.example.demo.domain.ofertas.OfertaDtoJobSearch;
import com.example.demo.services.ofertas.OfertaMapper;
import com.example.demo.services.ofertas.OfertaService;

@RequestMapping("/ofertas/api")
@RestController
public class OfertasController {

    @Autowired
    OfertaService ofertaService;

    @Autowired
    OfertaMapper ofertaMapper;

    @PostMapping("/busqueda")
    public ResponseEntity<Page<OfertaDtoJobSearch>> getAllOfertas(
            @RequestBody PaginaJobSearchRequest paginaJobSearchRequest) {
        if (paginaJobSearchRequest.getBusquedaOferta() != null) {
            Page<OfertaDtoJobSearch> resultado = ofertaService.obtenerPaginaOfertas(
                    paginaJobSearchRequest.getPagina(),
                    paginaJobSearchRequest.getBusquedaOferta());
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }

    @GetMapping("/obtenerOfertaPorId/jobSearch/{id}")
    public ResponseEntity<OfertaDtoJobSearch> getOfertaByIdApiJobSearch(@PathVariable Long id) {
        OfertaDtoJobSearch resultado = ofertaMapper.mapOfertaEntityToJobSearchDto(
                ofertaService.obtenerPorId(id));
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/obtenerOfertaPorId/employer/{id}")
    public ResponseEntity<OfertaDtoEmployer> getOfertaByIdApiEmployerDto(@PathVariable Long id) {
        OfertaDtoEmployer resultado = ofertaMapper.mapOfertaEntityToEmployerDto(
                ofertaService.obtenerPorId(id));
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PutMapping("/inscribirBusca/{id}")
    public ResponseEntity<Void> suscribeBuscaInOferta(@PathVariable Long id) {
        ofertaService.inscribirBuscaConectadoWrapper(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/desinscribirBusca/{id}")
    public ResponseEntity<Void> unsuscribeBuscaInOferta(@PathVariable Long id) {
        ofertaService.desinscribirBuscaConectadoWrapper(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
