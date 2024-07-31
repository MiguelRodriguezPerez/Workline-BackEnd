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

        if(usuarioService.obtenerUsuarioConectado().getRol() == Rol.BUSCA){
            Busca busca = (Busca) usuarioService.obtenerUsuarioConectado();
            model.addAttribute("usuarioLogueado",busca);
            model.addAttribute("listaConocimientos", busca.getListaConocimientos());
            model.addAttribute("listaExperiencias", busca.getListaExperiencias());
        }

        return "usuarioPerfil/indexPerfil";
    }

    @GetMapping("/editarDatos")
    public String showEditarDatosForm(Model model){
        model.addAttribute("usuarioLogueado", usuarioService.obtenerUsuarioConectado());
        //Devuelve una instancia de la clase padre, por lo que no podrás acceder a los datos exclusivos
        //de la clase hija

        return "usuarioPerfil/editarDatosPerfil";
    }
}
