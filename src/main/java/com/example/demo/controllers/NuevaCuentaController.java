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

import com.example.demo.domain.dtos.LoginUserDto;
import com.example.demo.domain.dtos.NuevoUsuarioDto;
import com.example.demo.domain.usuarios.usuario.LoggedUserContext;
import com.example.demo.domain.usuarios.usuario.Usuario;
import com.example.demo.services.auth.AuthenticationService;
import com.example.demo.services.usuarios.busca.BuscaService;
import com.example.demo.services.usuarios.contrata.ContrataService;
import com.example.demo.services.usuarios.usuario.UsuarioService;

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

        @PostMapping("/nuevoUsuario")
        public ResponseEntity<LoggedUserContext> createNewUserEndpoint(@RequestBody NuevoUsuarioDto dto) {
                switch (dto.getRol()) {
                        case BUSCA:
                                return createNewBuscaEndpoint(dto);
                        case CONTRATA:
                                return createNewContrataEndpoint(dto);
                        default:
                                return ResponseEntity.badRequest()
                                                .body(null);
                }
        }

        @PostMapping("/nuevoContrata")
        public ResponseEntity<LoggedUserContext> createNewContrataEndpoint(@RequestBody NuevoUsuarioDto dto) {
                contrataService.guardarNuevoUsuarioFromDto(dto);

                // Usas la contraseña del dto sin encriptar. La encriptada no serviría
                Usuario authenticatedUser = authenticationService.authenticate(
                                new LoginUserDto(dto.getNombre(), dto.getPassword()));
                ResponseCookie cookie = authenticationService.generateCookieToken(authenticatedUser);
                LoggedUserContext usuarioContext = authenticationService.getUsuarioViewClientContext(authenticatedUser);

                return ResponseEntity
                                .created(URI.create("/user/getCurrentUser"))
                                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                                .body(usuarioContext);
        }

        @PostMapping("/nuevoBusca")
        public ResponseEntity<LoggedUserContext> createNewBuscaEndpoint(@RequestBody NuevoUsuarioDto dto) {
                buscaService.guardarNuevoUsuarioFromDto(dto);

                // Usas la contraseña del dto sin encriptar. La encriptada no serviría
                Usuario authenticatedUser = authenticationService.authenticate(
                                new LoginUserDto(dto.getNombre(), dto.getPassword()));
                ResponseCookie cookie = authenticationService.generateCookieToken(authenticatedUser);
                LoggedUserContext usuarioContext = authenticationService.getUsuarioViewClientContext(authenticatedUser);

                return ResponseEntity
                                .created(URI.create("/user/getCurrentUser"))
                                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                                .body(usuarioContext);
        }

        @GetMapping("/esNombreRepetido/{nombre}")
        public ResponseEntity<Boolean> isUserRepeated(@PathVariable String nombre) {
                return new ResponseEntity<>(usuarioService.esNombreRepetido(nombre), HttpStatus.OK);
        }

}
