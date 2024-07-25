package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.usuarios.ContrataService;

@RequestMapping("/seccionContrata")
@Controller
public class ContrataUserController {

    @Autowired
    ContrataService contrataService;
    
    @GetMapping("/{numPag}")
    public String showIndexContrata(Model model, @PathVariable Integer numPag){
        model.addAttribute("listaOfertas", contrataService.obtenerPaginaOfertasPublicadas(numPag));
        model.addAttribute("numOfertas", contrataService.obtenerContrataConectado().getListaOfertas().size());
        
        return "contrataSeccion/indexContrata";
    }
}
