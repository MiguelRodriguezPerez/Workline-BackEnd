package com.example.demo.domain.usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioContext {

    private String nombre;
    private String email;
    private String rol;

}
