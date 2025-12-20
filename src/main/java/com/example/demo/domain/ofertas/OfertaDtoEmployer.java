package com.example.demo.domain.ofertas;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.domain.usuarios.busca.BuscaDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OfertaDtoEmployer {
    private Long id;
    private String puesto;
    private String sector;
    private String descripcion; // nullable
    private String ciudad;
    private Double salarioAnual; // nullable
    private TipoContrato tipoContrato;
    private Byte horas;
    private ModalidadTrabajo modalidadTrabajo;
    private LocalDate fechaPublicacion; 
    private List<BuscaDto> listaCandidatos; 
}
