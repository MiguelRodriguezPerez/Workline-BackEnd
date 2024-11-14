package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UsuarioDto;
import com.example.demo.domain.usuarios.UsuarioContext;
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

    public Usuario encontrarUsuarioPorNombre(String nombre) {

        if (contrataService.obtenerPorNombre(nombre) != null)
            return contrataService.obtenerPorNombre(nombre);
        else if (buscaService.obtenerPorNombre(nombre) != null)
            return buscaService.obtenerPorNombre(nombre);
        else if (adminService.obtenerPorNombre(nombre) != null)
            return adminService.obtenerPorNombre(nombre);
        else
            return null;

    }

    public UsuarioContext convertirUsuarioAUsuarioView(Usuario usuario) {
        return new UsuarioContext(usuario.getNombre(), usuario.getEmail(), usuario.getRol().toString());
    }

    public UsuarioDto convertirUsuarioAUsuarioDto(Usuario usuario) {
        return new UsuarioDto(usuario.getNombre(), usuario.getEmail(), usuario.getTelefono(), usuario.getCiudad());
    }

    public Usuario obtenerUsuarioLogueado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario currentUsuario = this.encontrarUsuarioPorNombre(authentication.getName());
        return currentUsuario;
    }

    public Usuario guardarCambios(UsuarioDto usuarioDto) {

        Usuario currentUsuario = this.obtenerUsuarioLogueado();

        currentUsuario.setNombre(usuarioDto.getNombre());
        currentUsuario.setEmail(usuarioDto.getEmail());
        currentUsuario.setTelefono(usuarioDto.getTelefono());
        currentUsuario.setCiudad(usuarioDto.getCiudad());

        switch (currentUsuario.getRol()) {

            case CONTRATA:
                Contrata contrata = (Contrata) currentUsuario;
                contrataService.guardarCambios(contrata);
                break;

        }

        return this.obtenerUsuarioLogueado();

    }

}
