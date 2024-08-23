package com.example.demo.controllers.miPerfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/miPerfil/busca")
public class MiPerfilBuscaController {

    @Autowired
    BuscaService buscaService;

    @Autowired
    ConocimientoService conocimientoService;

    @Autowired
    ExperienciaService experienciaService;

    @Autowired
    OfertaService ofertaService;
    
    @GetMapping("/")
    public String showUserInfo(Model model){
        model.addAttribute("buscaLogueado",buscaService.obtenerBuscaConectado());
        return "miPerfil/busca/indexPerfilBusca";
    }

    @GetMapping("/editarDatos")
    public String showEditarDatosForm(Model model){
        model.addAttribute("buscaLogueado", buscaService.obtenerBuscaConectado());
        return "miPerfil/busca/editarDatosBusca";
    }

    // @PostMapping("/editarDatos/CONTRATA/submit")
    // public String showSubmitEditarDatosCONTRATA(Contrata contrata){
    //     Contrata c2 = contrataService.guardarCambios(contrata);
    //     ofertaService.cambiarPropiedadOfertas(c2.getListaOfertas(), c2.getNombre());
    //     return "redirect:/miPerfil/";
    // }

    @PostMapping("/editarDatos/submit")
    public String showSubmitEditarDatos(Busca busca){
        buscaService.guardarCambios(busca);
        return "redirect:/miPerfil/busca/";
    }

    @GetMapping("/nuevoConocimiento")
    public String createNewConocimiento(Model model){
        model.addAttribute("conocimiento", new Conocimiento());
        model.addAttribute("buscaLogueado", buscaService.obtenerBuscaConectado());

        return "miPerfil/busca/nuevoConocimiento";
    }

    @PostMapping("/nuevoConocimiento/submit")
    public String submitNewConocimiento(Conocimiento conocimiento){
        conocimiento.setBusca(buscaService.obtenerBuscaConectado());
        buscaService.guardarSinEncriptar(buscaService.obtenerBuscaConectado());
        conocimientoService.guardarConocimiento(conocimiento);

        return "redirect:/miPerfil/busca/";
    }

    @GetMapping("/editarConocimiento/{idExp}")
    public String showEditConocimiento(@PathVariable Long idExp, Model model){
        model.addAttribute("conocimientoEditar", conocimientoService.obtenerPorId(idExp));
        return "miPerfil/busca/editarConocimiento";
    }

    @PostMapping("/editarConocimiento/submit")
    public String showSubmitEdittedConocimiento(Conocimiento conocimiento){
        conocimientoService.actualizarConocimiento(conocimiento);
        return "redirect:/miPerfil/busca/";
    }

    @GetMapping("/borrarConocimiento/{idCon}")
    public String showDeleteConocimiento(@PathVariable Long idCon){
        //Orden exacto. No cambiar
        buscaService.obtenerBuscaConectado().getListaConocimientos().remove(conocimientoService.obtenerPorId(idCon));
        conocimientoService.borrarConocimiento(idCon);
        buscaService.guardarSinEncriptar(buscaService.obtenerBuscaConectado());
        return "redirect:/miPerfil/busca/";
    }

    @GetMapping("/nuevaExperiencia")
    public String createNewExperiencia(Model model){
        model.addAttribute("titulo", "Escribe una nueva experiencia");
        model.addAttribute("experiencia", new Experiencia());
        model.addAttribute("buscaLogueado", buscaService.obtenerBuscaConectado());

        return "miPerfil/busca/nuevaExperiencia";
    }

    @PostMapping("/nuevaExperiencia/submit")
    public String submitNewExperiencia(Experiencia experiencia){
        experiencia.setBusca(buscaService.obtenerBuscaConectado());
        experienciaService.guardarExperiencia(experiencia);

        return "redirect:/miPerfil/busca/";
    }

    @GetMapping("/editarExperiencia/{idExp}")
    public String showEditExperiencia(@PathVariable Long idExp, Model model){
        model.addAttribute("titulo", "Editar experiencia");
        model.addAttribute("experiencia", experienciaService.obtenerPorId(idExp));
        model.addAttribute("buscaLogueado", buscaService.obtenerBuscaConectado());

        return "miPerfil/busca/nuevaExperiencia";
    }

    @GetMapping("/borrarExperiencia/{idExp}")
    public String showDeleteExperiencia(@PathVariable Long idExp){
        //Orden exacto. No cambiar
        buscaService.obtenerBuscaConectado().getListaExperiencias().remove(experienciaService.obtenerPorId(idExp));
        experienciaService.borrarExperiencia(idExp);
        buscaService.guardarSinEncriptar(buscaService.obtenerBuscaConectado());

        return "redirect:/miPerfil/busca/";
    }

    @GetMapping("/misOfertas/desinscribirse/{ofertaId}")
    public String showDesuscribeOferta(@PathVariable Long ofertaId){
        Busca busca = buscaService.obtenerBuscaConectado();
        Oferta oferta = ofertaService.obtenerPorId(ofertaId);

        busca.getListaOfertas().remove(oferta);
        oferta.getListaCandidatos().remove(busca);

        buscaService.guardarSinEncriptar(busca);
        ofertaService.guardarOferta(oferta);

        return "redirect:/miPerfil/busca/";
    }

    @GetMapping("/cambiarPassword/primerPaso")
    public String showFirstStepChangePassword(Model model){
        model.addAttribute("usuarioLogueado",buscaService.obtenerBuscaConectado());
        model.addAttribute("verificarPassword", new String());

        return "miPerfil/busca/passwords/confirmarPassword";
    }

    @PostMapping("/cambiarPassword/segundoPaso")
    public String showSecondStepChangePassword(@RequestParam String verificarPassword, Model model, RedirectAttributes redirectAttributes){
        if(buscaService.coincidePassword(verificarPassword)){
            model.addAttribute("usuarioLogueado", buscaService.obtenerBuscaConectado());
            return "miPerfil/busca/passwords/cambiarPassword";
        } 
        redirectAttributes.addFlashAttribute("errorConfirmarPassword", "Contraseña incorrecta");

        
        return "redirect:/miPerfil/busca/cambiarPassword/primerPaso";
    }

    @PostMapping("/cambiarPassword/tercerPaso")
    public String showThirdStepChangePassword(@RequestParam String nuevoPassword){
        buscaService.cambiarPassword(nuevoPassword);
        
        return "miPerfil/busca/passwords/exitoCambiarPassword";
    }

    @GetMapping("/borrarCuenta")
    public String deleteAccountBusca(HttpServletRequest request, HttpServletResponse response){
        Busca busca = buscaService.obtenerBuscaConectado();

        busca.getListaConocimientos().clear();
        busca.getListaExperiencias().clear();
        ofertaService.borrarBuscaTodasOfertas(busca);
        
        buscaService.borrar(busca.getId());

        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/";
    }
}
