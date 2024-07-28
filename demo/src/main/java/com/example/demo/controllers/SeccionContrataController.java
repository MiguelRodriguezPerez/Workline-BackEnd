package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.ContrataService;


@RequestMapping("/seccionContrata")
@Controller
public class SeccionContrataController {

    @Autowired
    ContrataService contrataService;

    @Autowired
    OfertaService ofertaService;
    
    @GetMapping("/pagina/{numPag}")
    public String showIndexContrata(Model model, @PathVariable Integer numPag){
        model.addAttribute("numPag", numPag);
        model.addAttribute("listaOfertas", contrataService.obtenerPaginaOfertasPublicadas(numPag));
        model.addAttribute("numOfertas", contrataService.obtenerContrataConectado().getListaOfertas().size());
        
        return "contrataSeccion/indexContrata";
    }

    @GetMapping("/siguientePag/{numPag}")
    public String isNextPageController(@PathVariable Integer numPag){
        return "redirect:/seccionContrata/pagina/" + contrataService.existeSiguientePagina(numPag);
    }

    @GetMapping("/anteriorPag/{numPag}")
    public String isPreviousPageController(@PathVariable Integer numPag){
        return "redirect:/seccionContrata/pagina/" + contrataService.existeAnteriorPagina(numPag);
    }

    @GetMapping("/pagina/{numPag}/detallesOferta/{ofertaId}")
    public String showOfertaDetails(@PathVariable Long numPag, @PathVariable Long ofertaId, Model model){
        model.addAttribute("numPag", numPag);
        model.addAttribute("ofertaId", ofertaId);
        model.addAttribute("ofertaSeleccionada", ofertaService.obtenerPorId(ofertaId));

        model.addAttribute("tiposContrato", TipoContrato.values());
        model.addAttribute("modalidadesTrabajo", ModalidadTrabajo.values());

        return "contrataSeccion/detallesOferta";
    }

    @PostMapping("/pagina/{numPag}/detallesOferta/{ofertaId}/editarOferta")
    public String updateOferta(@PathVariable Long numPag, @PathVariable Long ofertaId, Oferta oferta){
        oferta.setRequisitos(null);
        ofertaService.guardarOferta(oferta);

        return "redirect:/seccionContrata/pagina/" + numPag + "/detallesOferta/" + ofertaId;
    }

    @GetMapping("/pagina/{numPag}/nuevaOferta")
    public String showCreateNewOfferPage(@PathVariable Long numPag, Model model){
        model.addAttribute("nuevaOferta", new Oferta());
        model.addAttribute("numPag", numPag);

        model.addAttribute("tiposContrato", TipoContrato.values());
        model.addAttribute("modalidadesTrabajo", ModalidadTrabajo.values());

        return "contrataSeccion/nuevaOferta";
    }

    @PostMapping("/pagina/{numPag}/detallesOferta/{ofertaId}/crearNuevaOferta")
    public String showCreateNewOffer(@PathVariable Long numPag, Oferta oferta){
        oferta.setContrata(contrataService.obtenerContrataConectado());
        oferta.setRequisitos(null);
        System.out.println(oferta + "AAAAAAAAAAAAAAAAAAAAAAAAAAA");
        ofertaService.guardarOferta(oferta);
        contrataService.obtenerContrataConectado().getListaOfertas().add(oferta);

        return "redirect:/seccionContrata/pagina/" + numPag;
    }
    
}
