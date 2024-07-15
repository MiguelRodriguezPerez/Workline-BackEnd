package com.example.demo.domain.usuarios;

import java.util.List;

import com.example.demo.domain.ofertas.Oferta;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "listaOfertas")
@NoArgsConstructor
// @AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("Contrata")
@Entity
public class Contrata extends Usuario{

    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contrata")
    // @JoinColumn(name = "oferta_id")
    @Nullable
    private List <Oferta> listaOfertas;
    
    public Contrata(String nombre, String email, String ciudad, String telefono, String password) {
        super(nombre, email, ciudad, telefono, password,Rol.CONTRATA);
    }
}
