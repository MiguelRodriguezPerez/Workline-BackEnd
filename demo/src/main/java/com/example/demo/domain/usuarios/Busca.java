package com.example.demo.domain.usuarios;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "{listaOfertas,listaConocimientos,listaExperiencias}")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("Busca")
public class Busca extends Usuario{
    
    // @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "busca")
    // @JsonManagedReference
    // private Set<Experiencia> listaExperiencias;//Cambiar por hashset???

    // @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "busca")
    // @JsonManagedReference
    // private Set<Conocimiento> listaConocimientos;

    // @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    // @JoinTable(name="busca_oferta",joinColumns = @JoinColumn(name="busca_id"),inverseJoinColumns = @JoinColumn(name="oferta_id"))
    // @JsonManagedReference
    // private Set<Oferta> listaOfertas;

    /*Busca es la entidad propietaria porque cada vez que se añada
    un nuevo busca a la tabla que une las ofertas con los busca se actualizará*/

    public Busca(String nombre, String email, String ciudad, String telefono, String password) {
        super(nombre, email, ciudad, telefono, password,Rol.BUSCA);
    }
}
