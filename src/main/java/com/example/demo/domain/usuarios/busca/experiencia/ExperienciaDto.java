package com.example.demo.domain.usuarios.busca.experiencia;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExperienciaDto {
    private Long id;
    private String puesto;
    private String empresa;
    private String inicioExperiencia;
    private String finExperiencia;
}
