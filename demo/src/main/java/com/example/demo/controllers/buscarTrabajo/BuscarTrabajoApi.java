package com.example.demo.controllers.buscarTrabajo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.services.ofertas.OfertaService;

//Esta es la api que debería gestionar la paginación con criterios
@RestController
@RequestMapping("/solicitudOfertas")
public class BuscarTrabajoApi {

    @Autowired
    OfertaService ofertaService;

    @GetMapping("/existeSiguientePaginaSinCriterios/{pag}")
    public ResponseEntity<Boolean> isNextPageWithoutCriteria(@PathVariable Integer pag){
        boolean haySiguientePag = ofertaService.existeSiguientePagina(pag, null);
        return new ResponseEntity<>(haySiguientePag, HttpStatus.OK);
    }

    //Este es el PostMapping que no funciona. Ver SecurityFilterChain
    @PostMapping("/existeSiguientePaginaConCriterios")
    public ResponseEntity<Boolean> isNextPage(@RequestBody BusquedaOferta busqueda) {
        System.out.println(busqueda + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        //Si este postMapping funcionase deberías ver este print en la consola
        /*NOTA: De la lógica de la búsqueda ya me encargo yo*/
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
