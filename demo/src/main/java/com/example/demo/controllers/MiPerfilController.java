package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.Experiencia;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Rol;
import com.example.demo.services.ConocimientoService;
import com.example.demo.services.ExperienciaService;
import com.example.demo.services.usuarios.BuscaService;

@Controller
@RequestMapping("/miPerfil")
public class MiPerfilController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    BuscaService buscaService;

    @Autowired
    ConocimientoService conocimientoService;

    @Autowired
    ExperienciaService experienciaService;
    
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
}
