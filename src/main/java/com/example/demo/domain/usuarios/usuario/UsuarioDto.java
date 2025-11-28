package com.example.demo.domain.usuarios.usuario;

import com.example.demo.domain.usuarios.Rol;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
@Builder
public class UsuarioDto {
    private String nombre;
    private String email;
    private String ciudad;
    private Rol rol;
}
