package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.services.usuarios.ContrataService;

@RequestMapping("/contrata/api")
@RestController
public class MisOfertasController {

    @Autowired
    ContrataService contrataService;

    @GetMapping("/ofertas/pagina/{num}")
    public ResponseEntity<Page<Oferta>> getPaginaApi(@CookieValue(defaultValue = "no-cookie-found") String jwtToken, 
        @PathVariable int num) {
        Page<Oferta> resultado = contrataService.obtenerPaginaOfertasPublicadas(Integer.valueOf(num));
        System.out.println(resultado.toString() + "QUE ASCO DE VIDA");
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
