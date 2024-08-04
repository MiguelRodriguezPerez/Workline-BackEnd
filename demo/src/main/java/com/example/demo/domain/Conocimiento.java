package com.example.demo.domain;

import java.time.LocalDate;

import com.example.demo.domain.usuarios.Busca;
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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(of = "id")
@Data
@ToString(exclude = "busca")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conocimiento {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @NotNull
    @Size(max = 40, message = "Máximo 40 carácteres")
    private String centroEducativo;
    @NotNull
    @Size(max = 40, message = "Máximo 40 carácteres")
    private String titulo;
    @NotNull
    private LocalDate inicioPeriodoConocimiento;
    @NotNull
    private LocalDate finPeriodoConocimiento;

    @ManyToOne
    @JoinColumn(name = "busca_id")
    @JsonBackReference
    private Busca busca;
    
}
