package com.example.demo.services.usuarios.usuario;

import org.springframework.stereotype.Component;

import com.example.demo.domain.usuarios.usuario.UserContextInterface;
import com.example.demo.domain.usuarios.usuario.Usuario;
import com.example.demo.domain.usuarios.usuario.UsuarioDto;
import com.example.demo.domain.usuarios.usuario.UsuarioSettignsDto;

@Component
public class UsuarioMapper {
    
    public UsuarioDto mapUsuarioEntityToUsuarioDto (Usuario usuario) {
        return UsuarioDto.builder()
                    .nombre(usuario.getNombre())
                    .email(usuario.getEmail())
                    .ciudad(usuario.getCiudad())
                    .rol(usuario.getRol())
                    .build();
    }

    public UsuarioSettignsDto mapUsuarioEntityToUsuarioSettignsDto (Usuario usuario) {
        return UsuarioSettignsDto.builder()
                    .nombre(usuario.getNombre())
                    .email(usuario.getEmail())
                    .telefono(usuario.getTelefono())
                    .ciudad(usuario.getCiudad())
                    .build();
    }

    public UserContextInterface mapUsuarioEntityToUserContextInterface (Usuario usuario) {
        return UserContextInterface.builder()
            .username(usuario.getUsername())
            .email(usuario.getEmail())
            // Sospechoso de fallar
            // TODO: Verificar e implementar enum
            .rol(usuario.getRol().toString())
            .build();
    }
}
