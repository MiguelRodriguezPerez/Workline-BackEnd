package com.example.demo.domain.usuarios;

import java.util.List;

import com.example.demo.domain.ofertas.Oferta;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "listaOfertas")
@NoArgsConstructor
// @AllArgsConstructor
@Table(name="Contrata")
@Entity
public class Contrata extends Usuario{


    private String apiKey;
    @JsonManagedReference("contrata-oferta")
    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contrata")
    @Nullable
    private List <Oferta> listaOfertas;
    
    public Contrata(String nombre, String email, String ciudad, String telefono, String password) {
        super(nombre, email, ciudad, telefono, password,Rol.CONTRATA);
    }
}
