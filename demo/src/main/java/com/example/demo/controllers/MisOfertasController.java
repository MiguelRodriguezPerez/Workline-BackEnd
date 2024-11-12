package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.OfertaDtoApi;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.ContrataService;

@RequestMapping("/contrata/api")
@RestController
public class MisOfertasController {

    @Autowired
    ContrataService contrataService;

    @Autowired
    OfertaService ofertaService;

    @GetMapping("/ofertas/pagina/{num}")//Nota: Borraste @CookieValue(defaultValue = "no-cookie-found") String jwtToken, 
    public ResponseEntity<Page<Oferta>> getPaginaApi(@PathVariable int num) {
        Page<Oferta> resultado = contrataService.obtenerPaginaOfertasPublicadas(Integer.valueOf(num));
        System.out.println(resultado.toString() + "QUE ASCO DE VIDA");
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping("/nuevaOferta")
    public ResponseEntity<Oferta> registrarOferta(@RequestBody OfertaDtoApi ofertaDtoApi){
        System.out.println(ofertaDtoApi);
        Oferta oferta = ofertaService.convertirOfertaDtoApiAOferta(ofertaDtoApi);
        System.out.println(oferta);
        ofertaService.guardarOfertaFromContrata(oferta);
        return new ResponseEntity<>(oferta,HttpStatus.CREATED);
    }
}
