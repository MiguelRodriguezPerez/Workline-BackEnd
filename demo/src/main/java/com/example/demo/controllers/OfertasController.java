package com.example.demo.controllers;

import java.util.Arrays;
import java.util.List;

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
import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.services.ofertas.OfertaService;

@RequestMapping("/ofertas/api")
@RestController
public class OfertasController {

    @Autowired
    OfertaService ofertaService;

    @PostMapping("/busqueda")
    public ResponseEntity<Page<Oferta>> getAllOfertas(@RequestBody PaginaJobSearchRequest paginaJobSearchRequest) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        if(paginaJobSearchRequest.getBusquedaOferta() != null){
            return new ResponseEntity<>(ofertaService.obtenerPaginaApi(paginaJobSearchRequest.getPagina(), 
            paginaJobSearchRequest.getBusquedaOferta()), HttpStatus.OK);
        }
        else{
            System.out.println("LA BUSQUEDA OFERTA SI ES NULL");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/modalidades")
    public ResponseEntity<List<ModalidadTrabajo>> getAllModalidades(){
        List<ModalidadTrabajo> resultado = Arrays.asList(ModalidadTrabajo.values());
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/tiposContrato")
    public ResponseEntity<List<TipoContrato>> getAllTiposContrato(){
        List<TipoContrato> resultado = Arrays.asList(TipoContrato.values());
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/obtenerOfertaPorId/{id}")
    public ResponseEntity<Oferta> getOfertaByIdApi(@PathVariable Long id){
        if(id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        
        Oferta resultado = ofertaService.obtenerPorId(id);
        if(resultado == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PutMapping("/inscribirBusca/{id}")
    public ResponseEntity<Void> suscribeBuscaInOferta(@PathVariable Long id){
        ofertaService.inscribirBuscaConectadoWrapper(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/desinscribirBusca/{id}")
    public ResponseEntity<Void> unsuscribeBuscaInOferta(@PathVariable Long id){
        ofertaService.desinscribirBuscaConectadoWrapper(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
