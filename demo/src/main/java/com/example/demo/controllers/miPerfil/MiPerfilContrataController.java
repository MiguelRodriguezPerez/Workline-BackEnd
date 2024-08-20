package com.example.demo.controllers.miPerfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.ContrataService;


@Controller
@RequestMapping("/miPerfil/contrata")
public class MiPerfilContrataController {

    @Autowired
    ContrataService contrataService;

    @Autowired
    OfertaService ofertaService;
    
    @GetMapping("/")
    public String showUserInfo(Model model){
        model.addAttribute("contrataLogueado",contrataService.obtenerContrataConectado());
        return "miPerfil/contrata/indexPerfilContrata";
    }

    @GetMapping("/editarDatos")
    public String showEditarDatosForm(Model model){
        model.addAttribute("contrataLogueado", contrataService.obtenerContrataConectado());
        return "miPerfil/contrata/editarDatosContrata";
    }

    @PostMapping("/editarDatos/submit")
    public String showSubmitEditarDatos(Contrata contrata){
        Contrata c2 = contrataService.guardarCambios(contrata);
        ofertaService.cambiarPropiedadOfertas(c2.getListaOfertas(), c2.getNombre());

        return "redirect:/miPerfil/contrata/";
    }

    @GetMapping("/cambiarPassword/primerPaso")
    public String showFirstStepChangePassword(Model model){
        model.addAttribute("usuarioLogueado",contrataService.obtenerContrataConectado());
        model.addAttribute("verificarPassword", new String());

        return "miPerfil/contrata/passwords/confirmarPassword";
    }

    @PostMapping("/cambiarPassword/segundoPaso")
    public String showSecondStepChangePassword(@RequestParam String verificarPassword, Model model){
        if(contrataService.coincidePassword(verificarPassword)){
            model.addAttribute("usuarioLogueado", contrataService.obtenerContrataConectado());
            return "miPerfil/contrata/passwords/cambiarPassword";
        } 
        
        return "redirect:/miPerfil/contrata/cambiarPassword/primerPaso";
    }

    @PostMapping("/cambiarPassword/tercerPaso")
    public String showThirdStepChangePassword(@RequestParam String nuevoPassword){
        contrataService.cambiarPassword(nuevoPassword);
        
        return "miPerfil/contrata/passwords/exitoCambiarPassword";
    }
}
