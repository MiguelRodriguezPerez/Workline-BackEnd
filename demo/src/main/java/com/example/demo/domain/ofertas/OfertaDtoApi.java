package com.example.demo.domain.ofertas;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class OfertaDtoApi {

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
