package com.example.demo.controllers.buscarTrabajo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ofertas.OfertaService;

@RestController
@RequestMapping("/solicitudOfertas")
public class BuscarTrabajoApi {

    @Autowired
    OfertaService ofertaService;
    
    @GetMapping("/existeSiguientePagina/{busqueda}/{pag}")
    public ResponseEntity<Boolean> isNextPage(@PathVariable String busqueda,@PathVariable Integer pag){
        boolean haySiguientePag = ofertaService.existeSiguientePagina(pag, null);
        return new ResponseEntity<>(haySiguientePag, HttpStatus.OK);
    }
}
