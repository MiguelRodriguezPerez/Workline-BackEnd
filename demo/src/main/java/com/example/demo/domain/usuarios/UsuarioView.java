package com.example.demo.domain.usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioView {
    
    private String nombre;
    private String email;
    private String rol;

}
