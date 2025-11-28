package com.example.demo.domain.usuarios.usuario;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserContextInterface {

    private String username;
    private String email;
    private String rol;

}
