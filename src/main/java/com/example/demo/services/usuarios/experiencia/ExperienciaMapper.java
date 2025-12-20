package com.example.demo.services.usuarios.experiencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.domain.usuarios.busca.experiencia.Experiencia;
import com.example.demo.domain.usuarios.busca.experiencia.ExperienciaDto;

@Component
public class ExperienciaMapper {

    public Experiencia mapExperienciaDtoToEntity(ExperienciaDto experienciaDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return Experiencia.builder()
                .id(experienciaDto.getId())
                .puesto(experienciaDto.getPuesto())
                .empresa(experienciaDto.getEmpresa())
                .inicioExperiencia(
                    LocalDate.parse(experienciaDto.getInicioExperiencia(), formatter)
                )
                .finExperiencia(
                    LocalDate.parse(experienciaDto.getFinExperiencia(), formatter)
                )
                .build();
    }

    
    public ExperienciaDto mapExperienciaEntityToDto(Experiencia experiencia) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return ExperienciaDto.builder()
            .id(experiencia.getId())
            .puesto(experiencia.getPuesto())
            .empresa(experiencia.getEmpresa())
            .inicioExperiencia(
                experiencia.getInicioExperiencia().format(formatter)
            )
            .finExperiencia(
                experiencia.getFinExperiencia().format(formatter)
            )
            .build();
    }


    public Set<ExperienciaDto> mapExperienciaSetEntityToDto(Set<Experiencia> experiencias) {
        return experiencias.stream()
            .map(this::mapExperienciaEntityToDto)
            .collect(Collectors.toSet());
    }

}
