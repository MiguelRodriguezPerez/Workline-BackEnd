package com.example.demo.services.usuarios.conocimiento;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.domain.usuarios.busca.conocimiento.Conocimiento;
import com.example.demo.domain.usuarios.busca.conocimiento.ConocimientoDto;

@Component
public class ConocimientoMapper {

    public ConocimientoDto mapConocimientoEntityToDto(Conocimiento conocimiento) {
        return ConocimientoDto.builder()
            .id(conocimiento.getId())
            .centroEducativo(conocimiento.getCentroEducativo())
            .titulo(conocimiento.getTitulo())
            .inicioPeriodoConocimiento(conocimiento.getInicioPeriodoConocimiento())
            .finPeriodoConocimiento(conocimiento.getFinPeriodoConocimiento())
            .build();
    }

    public Set<ConocimientoDto> mapConocimientoSetEntityToDto(Set<Conocimiento> conocimientos) {
        return conocimientos.stream()
            .map(this::mapConocimientoEntityToDto) 
            .collect(Collectors.toSet());
    }


}
