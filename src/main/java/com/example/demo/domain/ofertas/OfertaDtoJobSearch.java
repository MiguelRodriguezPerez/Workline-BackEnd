package com.example.demo.domain.ofertas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

import com.example.demo.domain.usuarios.contrata.ContrataDto;


@Schema(
    description = "Job offer search result",
    example = """
    {
      "id": 12345,
      "puesto": "Backend Java Developer",
      "sector": "IT",
      "descripcion": "Development and maintenance of microservices using Spring Boot",
      "ciudad": "Madrid",
      "salarioAnual": 42000.50,
      "horas": 40,
      "fechaPublicacion": "2024-10-15",
      "tipoContrato": "INDEFINIDO",
      "modalidadTrabajo": "HIBRIDO",
      "numeroCandidatos": 12,
      "contrata": {
        "id": 3,
        "nombre": "Tech Solutions SL",
        "cif": "B12345678"
      }
    }
    """
)
@Getter
@ToString
@Builder
public class OfertaDtoJobSearch {

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
