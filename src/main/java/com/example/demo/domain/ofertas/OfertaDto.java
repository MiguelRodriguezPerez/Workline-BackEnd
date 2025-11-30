package com.example.demo.domain.ofertas;

import java.time.LocalDate;

import com.example.demo.domain.usuarios.contrata.ContrataDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class OfertaDto {

    private Long id;
    private String puesto;
    private String sector;
    private String descripcion;
    private String ciudad;
    private Double salarioAnual;
    private Byte horas;
    private LocalDate fechaPublicacion;
    private TipoContrato tipoContrato;
    private ModalidadTrabajo modalidadTrabajo;
    private int numeroCandidatos;
    private ContrataDto contrata;

}
