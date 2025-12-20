package com.example.demo.domain.usuarios.contrata;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.usuario.Rol;
import com.example.demo.domain.usuarios.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "listaOfertas")
@NoArgsConstructor
// @AllArgsConstructor
@Entity
public class Contrata extends Usuario {


    // private String apiKey;
    @JsonBackReference("contrata_oferta")
    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contrata")
    @Nullable
    private Set<Oferta> listaOfertas = new HashSet<>();
    
    public Contrata(String nombre, String email, String ciudad, String telefono, String password) {
        super(nombre, email, ciudad, telefono, password,Rol.CONTRATA);
    }
}
