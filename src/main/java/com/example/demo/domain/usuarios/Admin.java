package com.example.demo.domain.usuarios;

import com.example.demo.domain.usuarios.usuario.Rol;
import com.example.demo.domain.usuarios.usuario.Usuario;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;


@AllArgsConstructor
//@NoArgsConstructor Duplicate method Admin() in type Admin
@Entity
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorValue("Admin")//Susceptible de fallar
public class Admin extends Usuario {
    
    public Admin(String nombre,String email,String ciudad,String telefono,String password){
        super(nombre, email, ciudad, telefono, password,Rol.ADMIN);
    }
}
