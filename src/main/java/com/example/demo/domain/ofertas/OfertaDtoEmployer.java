package com.example.demo.domain.ofertas;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.usuarios.busca.BuscaDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(
    description = "Represents a job offer from an employer, including candidate list",
    example = """
    {
      "id": 101,
      "puesto": "Senior Backend Developer",
      "sector": "IT",
      "descripcion": "Responsible for designing and maintaining microservices",
      "ciudad": "Barcelona",
      "salarioAnual": 55000.0,
      "tipoContrato": "INDEFINIDO",
      "horas": 40,
      "modalidadTrabajo": "REMOTO",
      "fechaPublicacion": "2024-12-01",
      "listaCandidatos": [
        {
          "id": 1,
          "nombre": "Juan Pérez",
          "email": "juan.perez@example.com"
        },
        {
          "id": 2,
          "nombre": "María Gómez",
          "email": "maria.gomez@example.com"
        }
      ]
    }
    """
)
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
