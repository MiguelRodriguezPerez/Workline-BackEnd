package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.Experiencia;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.services.ConocimientoService;
import com.example.demo.services.ExperienciaService;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

@Controller
@RequestMapping("/miPerfil")
public class MiPerfilController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    BuscaService buscaService;

    @Autowired
    ContrataService contrataService;

    @Autowired
    ConocimientoService conocimientoService;

    @Autowired
    ExperienciaService experienciaService;

    @Autowired
    OfertaService ofertaService;
    
    @GetMapping("/")
    public String showUserInfo(Model model){
        model.addAttribute("usuarioLogueado",usuarioService.obtenerUsuarioConectado());
        System.out.println(usuarioService.obtenerUsuarioConectado());

        return "usuarioPerfil/indexPerfil";
    }

    @GetMapping("/editarDatos")
    public String showEditarDatosForm(Model model){
        model.addAttribute("usuarioLogueado", usuarioService.obtenerUsuarioConectado());
        return "usuarioPerfil/editarDatosPerfil";
    }

    @PostMapping("/editarDatos/CONTRATA/submit")
    public String showSubmitEditarDatosCONTRATA(Contrata contrata){
        Contrata c2 = contrataService.guardarCambios(contrata);
        ofertaService.cambiarPropiedadOfertas(c2.getListaOfertas(), c2.getNombre());
        return "redirect:/miPerfil/";
    }

    @PostMapping("/editarDatos/BUSCA/submit")
    public String showSubmitEditarDatos(Busca busca){
        buscaService.guardarCambios(busca);
        return "redirect:/miPerfil/";
    }

    @GetMapping("/nuevoConocimiento")
    public String createNewConocimiento(Model model){
        model.addAttribute("conocimiento", new Conocimiento());
        model.addAttribute("buscaLogueado", buscaService.obtenerBuscaConectado());

        return "usuarioPerfil/nuevoConocimiento";
    }

    @PostMapping("/nuevoConocimiento/submit")
    public String submitNewConocimiento(Conocimiento conocimiento){
        conocimiento.setBusca(buscaService.obtenerBuscaConectado());
        buscaService.guardarSinEncriptar(buscaService.obtenerBuscaConectado());
        conocimientoService.guardarConocimiento(conocimiento);

        return "redirect:/miPerfil/";
    }

    @GetMapping("/editarConocimiento/{idExp}")
    public String showEditConocimiento(@PathVariable Long idExp, Model model){
        model.addAttribute("conocimientoEditar", conocimientoService.obtenerPorId(idExp));
        return "usuarioPerfil/editarConocimiento";
    }

    @PostMapping("/editarConocimiento/submit")
    public String showSubmitEdittedConocimiento(Conocimiento conocimiento){
        conocimientoService.actualizarConocimiento(conocimiento);
        return "redirect:/miPerfil/";
    }

    @GetMapping("/borrarConocimiento/{idCon}")
    public String showDeleteConocimiento(@PathVariable Long idCon){
        //Orden exacto. No cambiar
        buscaService.obtenerBuscaConectado().getListaConocimientos().remove(conocimientoService.obtenerPorId(idCon));
        conocimientoService.borrarConocimiento(idCon);
        buscaService.guardarSinEncriptar(buscaService.obtenerBuscaConectado());
        return "redirect:/miPerfil/";
    }

    @GetMapping("/nuevaExperiencia")
    public String createNewExperiencia(Model model){
        model.addAttribute("titulo", "Escribe una nueva experiencia");
        model.addAttribute("experiencia", new Experiencia());
        model.addAttribute("buscaLogueado", buscaService.obtenerBuscaConectado());

        return "usuarioPerfil/nuevaExperiencia";
    }

    @PostMapping("/nuevaExperiencia/submit")
    public String submitNewExperiencia(Experiencia experiencia){
        experiencia.setBusca(buscaService.obtenerBuscaConectado());
        experienciaService.guardarExperiencia(experiencia);

        return "redirect:/miPerfil/";
    }

    @GetMapping("/editarExperiencia/{idExp}")
    public String showEditExperiencia(@PathVariable Long idExp, Model model){
        model.addAttribute("titulo", "Editar experiencia");
        model.addAttribute("experiencia", experienciaService.obtenerPorId(idExp));
        model.addAttribute("buscaLogueado", buscaService.obtenerBuscaConectado());

        return "usuarioPerfil/nuevaExperiencia";
    }

    @GetMapping("/borrarExperiencia/{idExp}")
    public String showDeleteExperiencia(@PathVariable Long idExp){
        //Orden exacto. No cambiar
        buscaService.obtenerBuscaConectado().getListaExperiencias().remove(experienciaService.obtenerPorId(idExp));
        experienciaService.borrarExperiencia(idExp);
        buscaService.guardarSinEncriptar(buscaService.obtenerBuscaConectado());

        return "redirect:/miPerfil/";
    }

    @GetMapping("/misOfertas/desinscribirse/{ofertaId}")
    public String showDesuscribeOferta(@PathVariable Long ofertaId){
        Busca busca = buscaService.obtenerBuscaConectado();
        Oferta oferta = ofertaService.obtenerPorId(ofertaId);

        busca.getListaOfertas().remove(oferta);
        oferta.getListaCandidatos().remove(busca);

        buscaService.guardarSinEncriptar(busca);
        ofertaService.guardarOferta(oferta);

        return "redirect:/miPerfil/";
    }

    @GetMapping("/cambiarPassword/primerPaso")
    public String showFirstStepChangePassword(Model model){
        model.addAttribute("usuarioLogueado",usuarioService.obtenerUsuarioConectado());
        model.addAttribute("verificarPassword", new String());

        return "usuarioPerfil/passwords/confirmarPassword";
    }

    @PostMapping("/cambiarPassword/segundoPaso")
    public String showSecondStepChangePassword(@RequestParam String verificarPassword, Model model){
        if(usuarioService.coincidePassword(verificarPassword)){
            model.addAttribute("usuarioLogueado", usuarioService.obtenerUsuarioConectado());
            return "usuarioPerfil/passwords/cambiarPassword";
        } 
        
        return "redirect:/miPerfil/cambiarPassword/primerPaso";
    }

    @PostMapping("/cambiarPassword/tercerPaso")
    public String showThirdStepChangePassword(@RequestParam String nuevoPassword){
        
        switch(usuarioService.obtenerUsuarioConectado().getRol().toString()){
            case "BUSCA":
                Busca busca = (Busca) usuarioService.obtenerUsuarioConectado();
                busca.setPassword(nuevoPassword);
                buscaService.guardarCambios(busca);
                break;
            case "CONTRATA":
                Contrata contrata = (Contrata) usuarioService.obtenerUsuarioConectado();
                contrata.setPassword(nuevoPassword);
                contrataService.guardarCambios(contrata);
                break;
        }
        
        return "usuarioPerfil/passwords/exitoCambiarPassword";
    }
}
