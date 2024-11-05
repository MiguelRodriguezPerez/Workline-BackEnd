package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entidadesApi.UserLoginRequest;
import com.example.demo.domain.usuarios.Admin;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.services.usuarios.AdminService;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

    @Autowired
    private AuthenticationManager authenticationManager; 

    @Autowired
    private SecurityContextRepository securityContextRepository;

    public boolean esNombreRepetido(String nombre){
        /*El primer if sirve para evitar que el usuario conectado no pueda actualizar sus datos
        con el mismo username. La comprobación de this.obtenerUsuarioConectado() != null sirve
        para que no impida la validación de la creación de un nuevo usuario 
        (Que lógicamente no inicio sesión)*/
        if(this.obtenerUsuarioConectado() != null && obtenerUsuarioConectado().getNombre().equals(nombre)) return false; //Si el usuario envía su propio nombre, no se impedirá la validación
        if(contrataService.esNombreRepetido(nombre)) return true;
        else if(buscaService.esNombreRepetido(nombre)) return true;
        else if(adminService.esNombreRepetido(nombre)) return true;
        else return false;
    }

    //Devuelve la instancia hija de usuario
    public Usuario obtenerUsuarioConectado(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication() + "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        if(contrataService.obtenerContrataConectado() != null) return (Contrata) contrataService.obtenerContrataConectado();
        if(buscaService.obtenerBuscaConectado() != null) return (Busca) buscaService.obtenerBuscaConectado();
        if(adminService.obtenerAdminConectado() != null) return (Admin) adminService.obtenerAdminConectado();
        return null;
    }

    public Usuario loginUsuario(UserLoginRequest userLoginRequest, HttpServletRequest request, HttpServletResponse response){

        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));
            if(authentication.isAuthenticated()) {
                System.out.println("BIIIIIIIEEEEEEN");
                SecurityContext securityContext = SecurityContextHolder.getContext(); 
                securityContext.setAuthentication(authentication); 
                securityContextRepository.saveContext(securityContext, request, response); 
                
                Usuario usuario = this.obtenerUsuarioConectado(); 
                return usuario;
            }
            
        } 
        catch (AuthenticationException e) {
            return null;
        }
        return null;
    }

    public boolean logoutUsuario(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            System.out.println("YASSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
            SecurityContextHolder.clearContext();
            return true;
        }
        return false;
    }

}
