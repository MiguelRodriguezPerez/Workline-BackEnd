package com.example.demo.domain.dtos.usuarios.oferta;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OfertaDto {

    private Long id;
    private String puesto;
    private String sector;
    private String descripcion;
    private String ciudad;
    private Double salarioAnual;
    // Futuro enum
    private String tipoContrato;
    private Byte horas;
    private String modalidadTrabajo;
    private List<String> listaValorables;
    private List<String> listaRequisitos;

}
