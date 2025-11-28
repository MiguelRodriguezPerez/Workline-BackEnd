package com.example.demo.domain.usuarios;

import java.util.Set;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.Experiencia;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@ToString(exclude = {"listaOfertas"}) // Excluir las colecciones relacionadas
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Busca extends Usuario {
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "busca")
    private Set<Experiencia> listaExperiencias;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "busca")
    private Set<Conocimiento> listaConocimientos;
 
    @JsonBackReference("busca_oferta") 
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
    @JoinTable(name = "busca_oferta", joinColumns = @JoinColumn(name = "busca_id"), inverseJoinColumns = @JoinColumn(name = "oferta_id")) 
    @JsonIgnoreProperties("busca_id") 
    private Set<Oferta> listaOfertas;

    public Busca(String nombre, String email, String ciudad, String telefono, String password) {
        super(nombre, email, ciudad, telefono, password, Rol.BUSCA);
    }
}
