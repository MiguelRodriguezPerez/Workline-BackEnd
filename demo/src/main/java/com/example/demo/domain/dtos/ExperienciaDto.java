package com.example.demo.domain.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExperienciaDto {
    
    private String puesto;
    private String empresa;
    private LocalDate inicioExperiencia;
    private LocalDate finExperiencia;

}
