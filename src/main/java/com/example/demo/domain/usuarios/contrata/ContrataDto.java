package com.example.demo.domain.usuarios.contrata;

import com.example.demo.domain.usuarios.usuario.Rol;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
@Builder
public class ContrataDto {
    private String nombre;
    private String email;
    private String ciudad;
    private Rol rol;
}
