package com.example.demo.domain.dtos;

import lombok.Getter;

//Entidad creación usuario
@Getter
public class RegisterUserDto {
    private String email;
    
    private String password;
    
    private String fullName;
    
}