package com.example.demo.domain.entidadesApi;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserLoginRequest {

    private String username;
    private String password;

}
