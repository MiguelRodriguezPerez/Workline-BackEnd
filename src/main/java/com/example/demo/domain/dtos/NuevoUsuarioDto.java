package com.example.demo.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NuevoUsuarioDto {

    private String nombre;
    private String email;
    private String telefono;
    private String ciudad;
    private String password;
    private String rol;

}
