package com.example.demo.services.usuarios.experiencia;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.domain.usuarios.busca.experiencia.Experiencia;
import com.example.demo.domain.usuarios.busca.experiencia.ExperienciaDto;

@Component
public class ExperienciaMapper {
    
    public ExperienciaDto mapExperienciaEntityToDto(Experiencia experiencia) {
        return ExperienciaDto.builder()
            .id(experiencia.getId())
            .puesto(experiencia.getPuesto())
            .empresa(experiencia.getEmpresa())
            .inicioExperiencia(experiencia.getInicioExperiencia())
            .finExperiencia(experiencia.getFinExperiencia())
            .build();
    }

    public Set<ExperienciaDto> mapExperienciaSetEntityToDto(Set<Experiencia> experiencias) {
        return experiencias.stream()
            .map(this::mapExperienciaEntityToDto)
            .collect(Collectors.toSet());
    }

}
