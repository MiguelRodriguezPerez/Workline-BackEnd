package com.example.demo.domain.usuarios;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.Experiencia;
import com.example.demo.domain.ofertas.Oferta;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString(exclude = {"listaOfertas"}) // Excluir las colecciones relacionadas
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Busca")
@Data
public class Busca extends Usuario {
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "busca")
    private Set<Experiencia> listaExperiencias = new HashSet<Experiencia>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "busca")
    private Set<Conocimiento> listaConocimientos = new HashSet<Conocimiento>();
 
    @JsonBackReference("busca-oferta") 
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
    @JoinTable(name = "busca_oferta", joinColumns = @JoinColumn(name = "busca_id"), inverseJoinColumns = @JoinColumn(name = "oferta_id")) 
    @JsonIgnoreProperties("busca_id") 
    private Set<Oferta> listaOfertas = new HashSet<Oferta>();

    public Busca(String nombre, String email, String ciudad, String telefono, String password) {
        super(nombre, email, ciudad, telefono, password, Rol.BUSCA);
    }
}
