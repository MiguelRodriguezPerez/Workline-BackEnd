package com.example.demo.domain.ofertas;

import java.util.List;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusquedaOferta {
    
    @Nullable
    private String puestoB;
    @Nullable
    private String sectorB;
    @Nullable
    private String tipoContratoB;
    @Nullable
    private String ciudadB;
    @Nullable
    private List<String> requisitos;
    @Nullable
    private Double salarioAnual;
    @Nullable
    private String modalidadB;
    
}
