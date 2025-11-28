package com.example.demo.domain.usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserContextInterface {

    private String username;
    private String email;
    private String rol;

}
