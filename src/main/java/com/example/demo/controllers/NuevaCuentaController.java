package com.example.demo.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.dtos.LoginUserDto;
import com.example.demo.domain.dtos.NuevoUsuarioDto;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UsuarioContext;
import com.example.demo.services.auth.AuthenticationService;
import com.example.demo.services.usuarios.BuscaService;
import com.example.demo.services.usuarios.ContrataService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/nuevaCuenta/api")
public class NuevaCuentaController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ContrataService contrataService;

    @Autowired
    BuscaService buscaService;


    @GetMapping("/esNombreRepetido/{nombre}")
    public ResponseEntity<Boolean> isUserRepeated(@PathVariable String nombre){
        //Sospechoso de fallar
        Boolean resultado = usuarioService.esNombreRepetido(nombre);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }
    

    //Discutiblemente podría ser PutMapping
    @PostMapping("/nuevoContrata")
    public ResponseEntity<UsuarioContext> createNewContrataEndpoint(@RequestBody NuevoUsuarioDto dto) {

        contrataService.guardarNuevoUsuarioFromDto(dto);
        //Asumiendo que todo vaya bien
        //Usas la contraseña del dto sin encriptar. La encriptada no serviría
        Usuario authenticatedUser = authenticationService.authenticate( 
            new LoginUserDto(dto.getNombre(), dto.getPassword())
        );
        ResponseCookie cookie = authenticationService.generateCookieToken(authenticatedUser);
        UsuarioContext usuarioContext = authenticationService.getUsuarioViewClientContext(authenticatedUser);
        
        /* TODO: Averiguar si tendría que existir un enpoint específico para cada usuario para indicar en el URI
        Ejemplo: /contrata/user/1 */

        return ResponseEntity
                .created(URI.create("/user/getCurrentUser"))
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(usuarioContext);
    }

    //Discutiblemente podría ser PutMapping
    @PostMapping("/nuevoBusca")
    public ResponseEntity<UsuarioContext> createNewBuscaEndpoint(@RequestBody NuevoUsuarioDto dto, HttpServletResponse response) {
        buscaService.guardarNuevoUsuarioFromDto(dto);
        //Asumiendo que todo vaya bien
        //Usas la contraseña del dto sin encriptar. La encriptada no serviría
        Usuario authenticatedUser = authenticationService.authenticate( 
            new LoginUserDto(dto.getNombre(), dto.getPassword())
        );
        ResponseCookie cookie = authenticationService.generateCookieToken(authenticatedUser);
        UsuarioContext usuarioContext = authenticationService.getUsuarioViewClientContext(authenticatedUser);
        
        /* TODO: Averiguar si tendría que existir un enpoint específico para cada usuario para indicar en el URI
        Ejemplo: /contrata/user/1 */

        return ResponseEntity
                .created(URI.create("/user/getCurrentUser"))
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(usuarioContext);
    }
    
}
