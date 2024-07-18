package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.services.ofertas.OfertaService;

@Controller
public class InicioController {

    

    @GetMapping({"/","/inicio"})
    public String getHomePage(){
        return "inicio";
    }

    
}
