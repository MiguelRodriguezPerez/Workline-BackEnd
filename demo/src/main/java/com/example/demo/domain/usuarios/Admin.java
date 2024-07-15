package com.example.demo.domain.usuarios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;


@AllArgsConstructor
//@NoArgsConstructor Duplicate method Admin() in type Admin
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("Admin")//Susceptible de fallar
public class Admin extends Usuario {
    
    public Admin(String nombre,String email,String ciudad,String telefono,String password){
        super(nombre, email, ciudad, telefono, password,Rol.ADMIN);
    }
}
