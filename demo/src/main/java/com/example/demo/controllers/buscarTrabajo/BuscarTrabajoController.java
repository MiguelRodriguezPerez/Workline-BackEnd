package com.example.demo.controllers.buscarTrabajo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.ofertas.OfertaService;

@Controller
@RequestMapping("/ofertasDeTrabajo")
public class BuscarTrabajoController {

    @Autowired
    OfertaService ofertaService;
    
    @GetMapping("/{numPag}")
    public String getOfferJobsPage(@PathVariable Integer numPag,Model model){
        model.addAttribute("listaOfertas", ofertaService.obtenerPagina(numPag, null));
        
        return "buscarTrabajo/indexBuscarTrabajo";
    }
}
