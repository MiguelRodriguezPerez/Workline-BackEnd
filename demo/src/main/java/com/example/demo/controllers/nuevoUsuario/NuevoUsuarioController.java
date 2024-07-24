package com.example.demo.controllers.nuevoUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.NuevoUsuario;
import com.example.demo.domain.usuarios.Rol;
import com.example.demo.services.usuarios.ContrataService;

@RequestMapping("/nuevoUsuario")
@Controller
public class NuevoUsuarioController {

    @Autowired
    ContrataService contrataService;
    
    @GetMapping("/")
    public String getNewUserForm(Model model){


        model.addAttribute("nuevoUsuario", new NuevoUsuario());
        model.addAttribute("listaRoles",Rol.rolesSinAdmin());
        return "nuevoUsuarioForm/primeraVista";
    }

    @PostMapping("/procesarUsuario")
    public String getNewUserFirstStep(NuevoUsuario nuevoUsuario){
        //De momento, solo serán contrata
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        contrataService.guardarContrataDesdeNuevoUsuario(nuevoUsuario);
        return "redirect:/";
    }
}
