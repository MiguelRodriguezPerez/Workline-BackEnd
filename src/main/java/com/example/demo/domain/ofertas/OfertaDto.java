package com.example.demo.domain.ofertas;

import com.example.demo.domain.usuarios.Contrata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class OfertaDto {

    private Long id;
    private String puesto;
    private String sector;
    private String descripcion;
    private String ciudad;
    private Double salarioAnual;
    // Futuro enum
    private TipoContrato tipoContrato;
    private Byte horas;
    private ModalidadTrabajo modalidadTrabajo;
    private int numeroCandidatos;
    private Contrata contrata;

}
