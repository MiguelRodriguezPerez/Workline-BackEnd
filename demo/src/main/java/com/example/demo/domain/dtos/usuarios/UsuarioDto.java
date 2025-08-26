package com.example.demo.domain.dtos.usuarios;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDto {
    
    private Long id;
    private String nombre;
    private String email;
    private String ciudad;
    private String telefono;

}
