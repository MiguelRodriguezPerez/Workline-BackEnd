package com.example.demo.services.usuarios.conocimiento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.domain.usuarios.busca.conocimiento.Conocimiento;
import com.example.demo.domain.usuarios.busca.conocimiento.ConocimientoDto;

@Component
public class ConocimientoMapper {


    /* En ts pase de complicarme la vida. Recibi las fechas como un string y este método usa un formater
    que traduce dicho string a un LocalDate para evitar formatear el formato de fecha de java por defecto */
    public Conocimiento mapConocimientoDtoToEntity (ConocimientoDto conocimientoDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return Conocimiento.builder()
               .id(conocimientoDto.getId())
                .centroEducativo(conocimientoDto.getCentroEducativo())
                .titulo(conocimientoDto.getTitulo())
                .inicioPeriodoConocimiento(
                    LocalDate.parse(conocimientoDto.getInicioPeriodoConocimiento(),formatter)
                )
                .finPeriodoConocimiento(
                    LocalDate.parse(conocimientoDto.getFinPeriodoConocimiento(), formatter)
                )
                .build(); 
    }

    /* En este método recibes la fecha de LocalDate en yyyy/MM/dd así que lo pasas a un string
    con el formato correcto al cliente */
    public ConocimientoDto mapConocimientoEntityToDto(Conocimiento conocimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return ConocimientoDto.builder()
            .id(conocimiento.getId())
            .centroEducativo(conocimiento.getCentroEducativo())
            .titulo(conocimiento.getTitulo())
            .inicioPeriodoConocimiento(
                conocimiento.getInicioPeriodoConocimiento().format(formatter)
            )
            .finPeriodoConocimiento(
                conocimiento.getFinPeriodoConocimiento().format(formatter)
            )
            .build();
    }

    public Set<ConocimientoDto> mapConocimientoSetEntityToDto(Set<Conocimiento> conocimientos) {
        return conocimientos.stream()
            .map(this::mapConocimientoEntityToDto) 
            .collect(Collectors.toSet());
    }


}
