package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/workline")
@Controller
public class BuscarTrabajoController {

    
    
    @GetMapping("/ofertasDeTrabajo")
    public String getOfferJobsPage(Model model){

    }
}
