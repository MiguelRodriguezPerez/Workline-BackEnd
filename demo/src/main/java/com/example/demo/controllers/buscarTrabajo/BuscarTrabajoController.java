package com.example.demo.controllers.buscarTrabajo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.LeerCSV;
import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.services.ofertas.OfertaService;

@Controller
@RequestMapping("/ofertasDeTrabajo")
public class BuscarTrabajoController {

    @Autowired
    OfertaService ofertaService;
    
    @GetMapping("/{numPag}")
    public String getOfferJobsPageWithoutSearch(@PathVariable Integer numPag,Model model){
        model.addAttribute("busquedaOferta", new BusquedaOferta());
        model.addAttribute("listaSectores", LeerCSV.procesarCSV("csv/listaSectores.csv"));
        model.addAttribute("tiposContrato", TipoContrato.values());
        model.addAttribute("tiposModalidad", ModalidadTrabajo.values());

        model.addAttribute("listaOfertas", ofertaService.obtenerPagina(numPag, null));
        
        return "buscarTrabajo/indexBuscarTrabajo";
    }

    @GetMapping("/resultadosBusqueda/{numPag}")
    public String showResults(BusquedaOferta busquedaOferta, @PathVariable Integer numPag, Model model){
        if(busquedaOferta.estaVacio()) return "redirect:/ofertasDeTrabajo/0";
        
        model.addAttribute("busquedaOferta", new BusquedaOferta());
        model.addAttribute("listaSectores", LeerCSV.procesarCSV("/csv/listaSectores.csv"));
        model.addAttribute("tiposContrato", TipoContrato.values());
        model.addAttribute("tiposModalidad", ModalidadTrabajo.values());

        model.addAttribute("listaOfertas", ofertaService.obtenerPagina(numPag, busquedaOferta));
        
        return "buscarTrabajo/indexBuscarTrabajo";

    }
}
