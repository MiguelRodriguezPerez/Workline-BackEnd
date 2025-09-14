package com.example.demo.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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

@RequestMapping("/user")
@RestController
public class MiPerfilController {

    /*Este controlador contiene los endpoints de acciones comunes de usuarios logueados que realizan
    acciones sobre su propia cuenta*/

    @Autowired
    UsuarioService usuarioService;

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
    public ResponseEntity<UsuarioContext> updateUserData(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuario = usuarioService.guardarCambios(usuarioDto);
        ResponseCookie jwtToken = authenticationService.generateCookieToken(usuario);
        UsuarioContext resultado = usuarioService.convertirUsuarioAUsuarioView(usuario);
        return ResponseEntity
        /* Esta considerado una buena práctica que cuando creas o actualizas un recurso en el servidor decirle 
         * al cliente a través de una URI en que subruta se encuentra dicho recurso. 
         * Además spring la fuerza de todas maneras 
        */
            .created(URI.create("/user/getCurrentUser"))
            .header(HttpHeaders.SET_COOKIE, jwtToken.toString())
            .body(resultado);
    }

    @DeleteMapping("/borrarCuentaUsuarioLogueado")
    public ResponseEntity<Void> deleteLoggedUserEndpoint() {
        usuarioService.borrarCuentaUsuarioLogueado();
        ResponseCookie jwtToken = authenticationService.logoutWrapper();       
        
        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, jwtToken.toString())
            .body(null);
    }

    @PostMapping("/confirmarPassword")
    public ResponseEntity<Boolean> checkPasswordEndpoint(@RequestBody String password){
        password = usuarioService.quitarComillasPassword(password);
        Boolean resultado = usuarioService.comprobarPasswordUsuarioLogueado(password);
        
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PutMapping("/cambiarPassword")
    public ResponseEntity<Void> changePasswordEndpoint(@RequestBody String newPassword){
        newPassword = usuarioService.quitarComillasPassword(newPassword);
        Usuario newUsuario = usuarioService.cambiarPasswordWrapper(newPassword);
        ResponseCookie jwtToken = authenticationService.generateCookieToken(newUsuario);
        
        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, jwtToken.toString())
            .body(null);
    }
}
