package com.example.demo.controllers.miPerfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UsuarioView;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@RestController
public class MiPerfilController {

    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("/getCurrentUser")
    public ResponseEntity<UsuarioView> getLoggedUser(HttpServletRequest request){
        
        Usuario usuario = usuarioService.obtenerUsuarioLogueado();
        UsuarioView usuarioView = usuarioService.convertirUsuarioAUsuarioView(usuario);

        return new ResponseEntity<>(usuarioView,HttpStatus.OK);
    }
}
