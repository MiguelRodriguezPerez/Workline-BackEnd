package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.BuscaService;

@Controller
@RequestMapping("/ofertasDeTrabajo")
public class BuscarTrabajoController {

    @Autowired
    OfertaService ofertaService;

    @Autowired
    BuscaService buscaService;
    
    @GetMapping("/{numPag}")
    public String getOfferJobsPageWithoutSearch(BusquedaOferta busquedaOferta,@PathVariable Integer numPag,Model model){
        model.addAttribute("busquedaOferta", busquedaOferta);
        model.addAttribute("numPag", numPag);
        model.addAttribute("numPaginas", ofertaService.obtenerNumeroPaginas(busquedaOferta));
        model.addAttribute("numOfertas", ofertaService.obtenerTodos().size());
        model.addAttribute("listaOfertas", ofertaService.obtenerPagina(numPag, busquedaOferta));

         // model.addAttribute("listaSectores", LeerCSV.procesarCSV("csv/listaSectores.csv"));
        model.addAttribute("tiposContrato", TipoContrato.values());
        model.addAttribute("tiposModalidad", ModalidadTrabajo.values());
        
        return "buscarTrabajo/indexBuscarTrabajo";
    }


    @GetMapping("/verOferta/{id}")
    public String showDetallesOferta(@PathVariable Long id,Model model){
        model.addAttribute("oferta", ofertaService.obtenerPorId(id));
        model.addAttribute("estaInscrito", buscaService.estaSuscritoOferta(id));

        return "buscarTrabajo/detallesOferta";
    }

    @GetMapping("/inscribirse/{ofertaId}")
    public String showSuscribedOferta(@PathVariable Long ofertaId) {
        Oferta oferta = ofertaService.obtenerPorId(ofertaId);
        Busca busca = buscaService.obtenerBuscaConectado();

        oferta.getListaCandidatos().add(busca);
        busca.getListaOfertas().add(oferta);

        ofertaService.guardarOferta(oferta);
        buscaService.guardarSinEncriptar(busca);

        return "redirect:/ofertasDeTrabajo/verOferta/" + ofertaId;
    }


    @GetMapping("/desinscribirse/{ofertaId}")
    public String showDesuscribedOferta(@PathVariable Long ofertaId){
        ofertaService.obtenerPorId(ofertaId).getListaCandidatos().remove(buscaService.obtenerBuscaConectado());
        buscaService.obtenerBuscaConectado().getListaOfertas().remove(ofertaService.obtenerPorId(ofertaId));

        ofertaService.guardarOferta(ofertaService.obtenerPorId(ofertaId));
        buscaService.guardarSinEncriptar(buscaService.obtenerBuscaConectado());

        return "redirect:/ofertasDeTrabajo/verOferta/" + ofertaId;
    }
}
