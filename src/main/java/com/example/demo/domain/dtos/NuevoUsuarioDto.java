package com.example.demo.domain.dtos;

import com.example.demo.domain.usuarios.usuario.Rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class NuevoUsuarioDto {

    private String nombre;
    private String email;
    private String telefono;
    private String ciudad;
    private String password;
    private Rol rol;

}
