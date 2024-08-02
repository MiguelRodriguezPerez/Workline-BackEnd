package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Rol;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.services.ConocimientoService;
import com.example.demo.services.usuarios.BuscaService;

@Controller
@RequestMapping("/miPerfil")
public class UsuarioPerfilController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    BuscaService buscaService;

    @Autowired
    ConocimientoService conocimientoService;
    
    @GetMapping("/")
    public String showUserInfo(Model model){
        model.addAttribute("usuarioLogueado",usuarioService.obtenerUsuarioConectado());

        if(usuarioService.obtenerUsuarioConectado().getRol() == Rol.BUSCA){
            Busca busca = (Busca) usuarioService.obtenerUsuarioConectado();
            model.addAttribute("listaConocimientos", busca.getListaConocimientos());
            model.addAttribute("listaExperiencias", busca.getListaExperiencias());
        }

        return "usuarioPerfil/indexPerfil";
    }

    @GetMapping("/editarDatos")
    public String showEditarDatosForm(Model model){
        model.addAttribute("usuarioLogueado", usuarioService.obtenerUsuarioConectado());
        return "usuarioPerfil/editarDatosPerfil";
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
        conocimientoService.guardarConocimiento(conocimiento);

        buscaService.obtenerBuscaConectado().getListaConocimientos().add(conocimiento);
        buscaService.guardarSinEncriptar(buscaService.obtenerBuscaConectado());

        return "redirect:/miPerfil/nuevoConocimiento";
    }
}
