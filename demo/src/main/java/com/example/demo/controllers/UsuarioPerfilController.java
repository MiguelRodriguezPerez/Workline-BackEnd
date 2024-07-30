package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Rol;

@Controller
@RequestMapping("/miPerfil")
public class UsuarioPerfilController {

    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("/")
    public String showUserInfo(Model model){
        model.addAttribute("usuarioLogueado", usuarioService.obtenerUsuarioConectado());

        if(usuarioService.obtenerUsuarioConectado().getRol() == Rol.BUSCA){
            Busca busca = (Busca) usuarioService.obtenerUsuarioConectado();
            model.addAttribute("listaConocimientos", busca.getListaConocimientos());
            model.addAttribute("listaExperiencias", busca.getListaExperiencias());
        }

        return "usuarioPerfil/indexPerfil";
    }
}
