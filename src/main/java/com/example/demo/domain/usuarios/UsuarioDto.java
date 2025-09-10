package com.example.demo.domain.usuarios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDto {
    
    private String nombre;
    private String email;
    private String telefono;
    private String ciudad;

}
