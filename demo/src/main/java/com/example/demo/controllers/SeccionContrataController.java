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
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;


@RequestMapping("/seccionContrata")
@Controller
public class SeccionContrataController {

    @Autowired
    ContrataService contrataService;

    @Autowired
    OfertaService ofertaService;

    @Autowired 
    BuscaService buscaService;
    
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
        model.addAttribute("oferta", ofertaService.obtenerPorId(ofertaId));
        model.addAttribute("listaCandidatos", ofertaService.obtenerPorId(ofertaId).getListaCandidatos());
        System.out.println("Lista de candidatos: " + ofertaService.obtenerPorId(ofertaId).getListaCandidatos()); // Log candidates

        return "contrataSeccion/detallesOferta";
    }


    @GetMapping("/pagina/{numPag}/nuevaOferta")
    public String showCreateNewOfferPage(@PathVariable Long numPag, Model model){
        model.addAttribute("nuevaOferta", new Oferta());
        model.addAttribute("numPag", numPag);

        model.addAttribute("tiposContrato", TipoContrato.values());
        model.addAttribute("modalidadesTrabajo", ModalidadTrabajo.values());

        return "contrataSeccion/nuevaOferta";
    }

    @PostMapping("/pagina/{numPag}/detallesOferta/{ofertaId}/guardarOferta")
    public String updateOferta(@PathVariable Long numPag, @PathVariable Long ofertaId, Oferta oferta){
        ofertaService.guardarOfertaFromContrata(oferta);

        return "redirect:/seccionContrata/pagina/" + numPag + "/detallesOferta/" + ofertaId;
    }

    @GetMapping("/pagina/{numPag}/oferta/editarOferta/{ofertaId}")
    public String showEditOffer(@PathVariable Long numPag, @PathVariable Long ofertaId, Model model){
        model.addAttribute("nuevaOferta", ofertaService.obtenerPorId(ofertaId));
        model.addAttribute("numPag", numPag);

        model.addAttribute("tiposContrato", TipoContrato.values());
        model.addAttribute("modalidadesTrabajo", ModalidadTrabajo.values());

        return "contrataSeccion/nuevaOferta";
    }

    @PostMapping("/pagina/{numPag}/detallesOferta/{ofertaId}/crearNuevaOferta")
    public String showCreateNewOffer(@PathVariable Long numPag, Oferta oferta){
        ofertaService.guardarOfertaFromContrata(oferta);

        return "redirect:/seccionContrata/pagina/" + numPag;
    }

    @GetMapping("/pagina/{numPag}/oferta/borrarOferta/{ofertaId}")
    public String deleteOffer(@PathVariable Long numPag, @PathVariable Long ofertaId){
        /*Tuviste que hacerlo todo aquí en vez de en ofertaService porque al inyectar
        buscaService en ofertaService provocaste una referencia circular*/
        Contrata contrata = contrataService.obtenerContrataConectado();
        Oferta oferta = ofertaService.obtenerPorId(ofertaId);

        buscaService.borrarCandidatosOferta(oferta);

        contrata.getListaOfertas().remove(oferta);
        contrataService.guardarSinEncriptar(contrata);

        ofertaService.guardarOferta(oferta);
        ofertaService.borrarOferta(ofertaId);

        return "redirect:/seccionContrata/pagina/" + numPag;
    }
    
}
