package com.example.demo.controllers.nuevoUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.NuevoUsuario;
import com.example.demo.domain.usuarios.Rol;
import com.example.demo.domain.usuarios.buscaData.Busca;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

@RequestMapping("/nuevoUsuario")
@Controller
public class NuevoUsuarioController {

    @Autowired
    ContrataService contrataService;

    @Autowired
    BuscaService buscaService;
    
    @GetMapping("/")
    public String getNewUserForm(Model model){


        model.addAttribute("nuevoUsuario", new NuevoUsuario());
        model.addAttribute("listaRoles",Rol.rolesSinAdmin());
        return "nuevoUsuarioForm/primeraVista";
    }

    @PostMapping("/procesarUsuario")
    public String getNewUserFirstStep(NuevoUsuario nuevoUsuario, RedirectAttributes redirectAttributes){
        if(nuevoUsuario.getRol() == Rol.CONTRATA){
            contrataService.guardarContrataDesdeNuevoUsuario(nuevoUsuario);
            return "redirect:/";
        } 
        else{
            System.out.println(nuevoUsuario + "CCCCCCCCCCCCCCCCCCCCCCCCCCCC");
            Busca busca = buscaService.convertirNuevoUsuario(nuevoUsuario);
            System.out.println(busca + "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
            redirectAttributes.addFlashAttribute("nuevoBusca", busca);
            return "redirect:/nuevoUsuario/segundaVista";
        }
    }

    @GetMapping("/segundaVista")
    public String getUserFormPartTwo(Model model){
        Busca busca = (Busca) model.getAttribute("nuevoBusca");
        System.out.println(busca + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return "redirect:/";
    }
}
