package com.example.demo.controllers.nuevoUsuario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.NuevoUsuario;

@RequestMapping("/nuevoUsuario")
@Controller
public class NuevoUsuarioController {
    
    @GetMapping("/")
    public String getNewUserForm(Model model){
        model.addAttribute("nuevoUsuario", new NuevoUsuario());
        return "nuevoUsuarioForm/primeraVista";
    }

    @PostMapping("/procesarUsuario")
    public String getNewUserFirstStep(NuevoUsuario nuevoUsuario){
        //De momento, solo serán contrata
        return null;
    }
}
