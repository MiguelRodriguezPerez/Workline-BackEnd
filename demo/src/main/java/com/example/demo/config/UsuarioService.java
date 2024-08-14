package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.usuarios.Admin;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.domain.usuarios.Rol;
import com.example.demo.domain.usuarios.Usuario;
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

    @Autowired
    PasswordEncoder encoder;

    public boolean esNombreRepetido(String nombre){
        // if(obtenerUsuarioConectado().getNombre().equals(nombre)) return false; //Si el usuario envía su propio nombre, no se impedirá la validación
        if(contrataService.esNombreRepetido(nombre)) return true;
        else if(buscaService.esNombreRepetido(nombre)) return true;
        else if(adminService.esNombreRepetido(nombre)) return true;
        else return false;
    }

    //Devuelve la instancia hija de usuario
    public Usuario obtenerUsuarioConectado(){
        if(contrataService.obtenerContrataConectado() != null) return (Contrata) contrataService.obtenerContrataConectado();
        if(buscaService.obtenerBuscaConectado() != null) return (Busca) buscaService.obtenerBuscaConectado();
        if(adminService.obtenerAdminConectado() != null) return (Admin) adminService.obtenerAdminConectado();
        return null;
    }

    public boolean coincidePassword(String verificarPassword){
        return encoder.matches(verificarPassword, this.obtenerUsuarioConectado().getPassword());
    }
}
