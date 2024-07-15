package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/workline")
@Controller
public class InicioController {

    @GetMapping({"/","/inicio"})
    public String getHomePage(){
        return "inicio";
    }

    
}
