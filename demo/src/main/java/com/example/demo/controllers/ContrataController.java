package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.OfertaDtoApi;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.ContrataService;

@RequestMapping("/contrata/api")
@RestController
public class ContrataController {

    @Autowired
    ContrataService contrataService;

    @Autowired
    OfertaService ofertaService;

    @GetMapping("/ofertas/pagina/{num}")//Nota: Borraste @CookieValue(defaultValue = "no-cookie-found") String jwtToken, 
    public ResponseEntity<Page<Oferta>> getPaginaApi(@PathVariable int num) {
        Page<Oferta> resultado = contrataService.obtenerPaginaOfertasPublicadas(Integer.valueOf(num));
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping("/nuevaOferta")
    public ResponseEntity<Oferta> registrarOferta(@RequestBody OfertaDtoApi ofertaDtoApi){
        Oferta oferta = ofertaService.convertirOfertaDtoApiAOferta(ofertaDtoApi);
        ofertaService.guardarOfertaFromContrata(oferta);
        return new ResponseEntity<>(oferta,HttpStatus.CREATED);
    }

    @PutMapping("/editarOferta/{id}")//Recibe el id por separado para saber que oferta editar
    public ResponseEntity<Oferta> actualizarOferta(@PathVariable Long id, @RequestBody OfertaDtoApi ofertaDtoApi){
        Oferta o1 = ofertaService.convertirOfertaDtoApiAOferta(ofertaDtoApi);
        o1.setId(id);
        Oferta ofertaDef = ofertaService.guardarCambios(o1);
        return new ResponseEntity<>(ofertaDef,HttpStatus.CREATED);
    }

    @DeleteMapping("/borrarOferta/{id}")
    public ResponseEntity<Void> borrarOferta(@PathVariable Long id){
        ofertaService.borrarOfertaWrapper(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

}
