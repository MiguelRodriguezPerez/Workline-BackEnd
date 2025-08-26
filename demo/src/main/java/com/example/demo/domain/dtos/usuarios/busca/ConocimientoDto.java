package com.example.demo.domain.dtos.usuarios.busca;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConocimientoDto {
    
    private String centroEducativo;
    private String titulo;
    private LocalDate inicioPeriodoConocimiento;
    private LocalDate finPeriodoConocimiento;
    
}
