package com.example.demo.domain.usuarios.busca.experiencia;

import java.time.LocalDate;

import com.example.demo.domain.usuarios.busca.Busca;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(of = "id")
@Data
@Builder
@ToString(exclude = "busca")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Experiencia {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @NotNull
    @Size(max = 40, message = "Máximo 40 carácteres puesto")
    private String puesto;

    @NotNull
    @Size(max = 40, message = "Máximo 40 carácteres experiencia")
    private String empresa;

    @NotNull
    private LocalDate inicioExperiencia; 

    @NotNull 
    private LocalDate finExperiencia;

    @ManyToOne
    @JoinColumn(name = "busca_id")
    @JsonBackReference(value = "busca_experiencia")
    private Busca busca;
 
}
