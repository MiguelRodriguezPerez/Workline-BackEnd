package com.example.demo.controllers.globalControllers.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.usuarios.ContrataService;

@RestController
@RequestMapping("/api/contrata")
public class ContrataApiController {

    @Autowired
    ContrataService contrataService;
    
    @GetMapping("/generarApiKey")
    public ResponseEntity<Boolean> generateApiKey(){
        if(contrataService.obtenerContrataConectado() == null) return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
        contrataService.generarApiKey();
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
