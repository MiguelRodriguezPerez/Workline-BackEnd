package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.ofertas.OfertaService;

@Controller
@RequestMapping("/ofertasDeTrabajo")
public class BuscarTrabajoController {

    @Autowired
    OfertaService ofertaService;
    
    @GetMapping("/")
    public String getOfferJobsPage(Model model){
        model.addAttribute("listaOfertas", ofertaService.obtenerPagina(0, null));
        
        return "buscarTrabajo/indexBuscarTrabajo";
    }
}
