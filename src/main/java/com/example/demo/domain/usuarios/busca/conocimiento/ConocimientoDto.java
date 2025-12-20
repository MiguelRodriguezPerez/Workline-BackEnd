package com.example.demo.domain.usuarios.busca.conocimiento;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConocimientoDto {
    private Long id;
    private String centroEducativo;
    private String titulo;
    private String inicioPeriodoConocimiento;
    private String finPeriodoConocimiento;
}