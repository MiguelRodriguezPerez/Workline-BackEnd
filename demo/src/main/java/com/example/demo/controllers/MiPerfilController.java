package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UsuarioContext;
import com.example.demo.domain.usuarios.UsuarioDto;
import com.example.demo.services.auth.AuthenticationService;
import com.example.demo.services.usuarios.BuscaService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/user")
@RestController
public class MiPerfilController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    BuscaService buscaService;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/getCurrentUser")
    public ResponseEntity<UsuarioContext> getLoggedUser() {
        Usuario usuario = usuarioService.obtenerUsuarioLogueado();
        UsuarioContext usuarioView = usuarioService.convertirUsuarioAUsuarioView(usuario);

        return new ResponseEntity<>(usuarioView, HttpStatus.OK);
    }

    @GetMapping("/getUserData")
    public ResponseEntity<UsuarioDto> getUserData() {
        UsuarioDto usuarioDto = usuarioService.convertirUsuarioAUsuarioDto(usuarioService.obtenerUsuarioLogueado());
        return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
    }

    @PutMapping("/updateUserData")
    public ResponseEntity<UsuarioContext> updateUserData(@RequestBody UsuarioDto usuarioDto, 
        HttpServletResponse response) {
            
        Usuario usuario = usuarioService.guardarCambios(usuarioDto);
        Cookie jwtToken = authenticationService.generateCookieToken(usuario);
        response.addCookie(jwtToken);
        UsuarioContext resultado = usuarioService.convertirUsuarioAUsuarioView(usuario);

        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @DeleteMapping("/borrarCuentaUsuarioLogueado")
    public ResponseEntity<Void> deleteLoggedUserEndpoint(HttpServletResponse response) {

        Usuario usuario = usuarioService.obtenerUsuarioLogueado();
        Cookie cookie = authenticationService.generateCookieToken(usuario);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        usuarioService.borrarCuentaUsuarioLogueado();
        authenticationService.logout();
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/confirmarPassword")
    public ResponseEntity<Boolean> checkPasswordEndpoint(@RequestBody String password){
        password = usuarioService.quitarComillasPassword(password);
        Boolean resultado = usuarioService.comprobarPasswordUsuarioLogueado(password);
        
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PutMapping("/cambiarPassword")
    public ResponseEntity<Void> changePasswordEndpoint(@RequestBody String newPassword,
        HttpServletResponse response){
        newPassword = usuarioService.quitarComillasPassword(newPassword);
        Usuario newUsuario = usuarioService.cambiarPasswordWrapper(newPassword);

        Cookie jwtToken = authenticationService.generateCookieToken(newUsuario);
        response.addCookie(jwtToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
