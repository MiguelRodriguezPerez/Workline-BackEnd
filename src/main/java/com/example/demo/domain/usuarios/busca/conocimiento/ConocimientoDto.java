package com.example.demo.domain.usuarios.busca.conocimiento;


import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConocimientoDto {
    private Long id;
    private String centroEducativo;
    private String titulo;
    private LocalDate inicioPeriodoConocimiento;
    private LocalDate finPeriodoConocimiento;
}