package com.example.demo.domain.usuarios.usuario;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioSettignsDto {

    private String nombre;
    private String email;
    private String telefono;
    private String ciudad;

}
