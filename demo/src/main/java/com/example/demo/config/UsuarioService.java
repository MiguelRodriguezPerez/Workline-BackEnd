package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.services.usuarios.AdminService;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

@Service
public class UsuarioService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    ContrataService contrataService;

    @Autowired
    BuscaService buscaService;

    @Autowired 
    AdminService adminService;

    public void actualizarAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    public boolean esNombreRepetido(String nombre){
        if(contrataService.esNombreRepetido(nombre)) return true;
        else if(buscaService.esNombreRepetido(nombre)) return true;
        else if(adminService.esNombreRepetido(nombre)) return true;
        else return false;
    }
}
