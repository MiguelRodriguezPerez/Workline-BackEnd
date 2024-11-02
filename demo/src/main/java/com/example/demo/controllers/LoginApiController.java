package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.entidadesApi.UserLoginRequest;
import com.example.demo.domain.usuarios.Usuario;

import jakarta.annotation.Resource;


@RequestMapping("/api/logins")
@RestController
public class LoginApiController {

    @Resource(name = "authenticationManager")
    private AuthenticationManager authenticationManager; 

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UsuarioService usuarioService;
    
    @PostMapping("/login")
    public ResponseEntity<Usuario> postMethodName(@RequestBody UserLoginRequest user) {
        
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if(authentication.isAuthenticated()) {
                System.out.println("BIIIIIIIEEEEEEN");
                SecurityContextHolder.getContext().setAuthentication(authentication);
                Usuario usuario = usuarioService.obtenerUsuarioConectado();
                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }
                
            } 
            catch (AuthenticationException e) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> postLogout() {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null){
                System.out.println("YASSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                SecurityContextHolder.clearContext();
                return new ResponseEntity<>(HttpStatus.OK); 
            }
            
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }
    

}
