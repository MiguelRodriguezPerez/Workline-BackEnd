package com.example.demo.controllers.globalControllers.apiControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entidadesApi.OfertaApi;
import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.services.ofertas.OfertaService;

@RequestMapping("/internal-api/ofertas")
@RestController
public class OfertasApiController {

    @Autowired
    OfertaService ofertaService;
    
    @GetMapping("/all")
    public ResponseEntity<List<OfertaApi>> getAllOfertas(){
        return new ResponseEntity<>(ofertaService.obtenerTodosApi(),HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<OfertaApi>> getResults(@RequestBody BusquedaOferta busquedaOferta){
        System.out.println(busquedaOferta);
        List<OfertaApi> resultado = ofertaService.obtenerResultadosApi(busquedaOferta);
        System.out.println(resultado.size() + "aaaaaaaaaaa");
        if(resultado.size() == 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

}
