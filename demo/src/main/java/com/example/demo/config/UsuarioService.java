package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UsuarioView;
import com.example.demo.services.usuarios.AdminService;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

@Service
public class UsuarioService {

    @Autowired
    ContrataService contrataService;

    @Autowired
    BuscaService buscaService;

    @Autowired
    AdminService adminService;

    public Usuario encontrarUsuarioPorNombre(String nombre){

        if(contrataService.obtenerPorNombre(nombre) != null) return contrataService.obtenerPorNombre(nombre);
        else if(buscaService.obtenerPorNombre(nombre) != null) return buscaService.obtenerPorNombre(nombre);
        else if(adminService.obtenerPorNombre(nombre) != null) return adminService.obtenerPorNombre(nombre);
        else return null;

    }

    public UsuarioView convertirUsuarioAUsuarioView(Usuario usuario){
        return new UsuarioView(usuario.getNombre(), usuario.getEmail(), usuario.getRol().toString());
    }

    public Usuario obtenerUsuarioLogueado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario currentUsuario = this.encontrarUsuarioPorNombre(authentication.getName());
        return currentUsuario;
    }

    
}
