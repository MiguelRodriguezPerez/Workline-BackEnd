package com.example.demo.controllers.nuevoUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.Experiencia;
import com.example.demo.domain.NuevoUsuario;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Rol;
import com.example.demo.services.ConocimientoService;
import com.example.demo.services.ExperienciaService;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

@RequestMapping("/nuevoUsuario")
@Controller
public class NuevoUsuarioController {

    @Autowired
    ContrataService contrataService;

    @Autowired
    BuscaService buscaService;

    @Autowired
    ConocimientoService conocimientoService;

    @Autowired
    ExperienciaService experienciaService;
    
    @GetMapping("/")
    public String getNewUserForm(Model model){


        model.addAttribute("nuevoUsuario", new NuevoUsuario());
        model.addAttribute("listaRoles",Rol.rolesSinAdmin());
        return "nuevoUsuarioForm/primeraVista";
    }

    @PostMapping("/procesarUsuario")
    public String getNewUserFirstStep(NuevoUsuario nuevoUsuario){
        if(nuevoUsuario.getRol() == Rol.CONTRATA){
            contrataService.guardarContrataDesdeNuevoUsuario(nuevoUsuario);
            return "redirect:/";
        } 
        else{
            Busca busca = buscaService.guardarBuscaDesdeNuevoUsuario(nuevoUsuario);
            return "redirect:/nuevoUsuario/segundaVista/" + busca.getId();
            // RedirectAttributes redirectAttributes
            // redirectAttributes.addFlashAttribute("nuevoBusca", busca);
            // Busca nuevoBusca = (Busca) model.getAttribute("nuevoBusca");
        }
    }

    @GetMapping("/segundaVista/{id}")
    public String getUserFormPartTwo(@PathVariable Long id,Model model){      
        model.addAttribute("nuevoBusca",buscaService.obtenerPorId(id));
        model.addAttribute("nuevoConocimiento", new Conocimiento());
        model.addAttribute("nuevaExperiencia", new Experiencia());

        model.addAttribute("nombreBusca", buscaService.obtenerPorId(id).getNombre());
        // model.addAttribute("idBusca", id);

        model.addAttribute("listaConocimientos", buscaService.obtenerPorId(id).getListaConocimientos());
        model.addAttribute("listaExperiencias", buscaService.obtenerPorId(id).getListaExperiencias());

        return "nuevoUsuarioForm/segundaVista";
    }

    @PostMapping("/segundaVista/subirConocimiento/{id}")
    public String updateNewUserConocimientos(@PathVariable Long id, Conocimiento conocimiento){
        Busca busca = buscaService.obtenerPorId(id);
        
        conocimiento.setBusca(busca);//Discutible
        conocimientoService.guardarConocimiento(conocimiento);
        busca.getListaConocimientos().add(conocimiento);
        buscaService.guardar(busca);

        return "redirect:/nuevoUsuario/segundaVista/" + id;
    }

    @PostMapping("/segundaVista/subirExperiencia/{id}")
    public String updateNewUserExperiencia(@PathVariable Long id, Experiencia experiencia){
        Busca busca = buscaService.obtenerPorId(id);

        experiencia.setBusca(busca);
        experienciaService.guardarExperiencia(experiencia);
        busca.getListaExperiencias().add(experiencia);
        buscaService.guardar(busca);

        return "redirect:/nuevoUsuario/segundaVista/" + id;
    }
}
