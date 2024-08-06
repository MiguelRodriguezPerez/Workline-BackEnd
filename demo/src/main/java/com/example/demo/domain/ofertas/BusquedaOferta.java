package com.example.demo.domain.ofertas;

import java.util.List;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BusquedaOferta {
    //Este es el objeto que estoy intentando que reciba la api BuscarTrabajoApi
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
    @Nullable
    private Long numPag;

    public boolean estaVacio(){
        if(!puestoB.isEmpty()) return false;
        if(!sectorB.equals("placeholder")) return false;
        if(!tipoContratoB.isEmpty()) return false;
        if(!ciudadB.isEmpty()) return false;
        if(requisitos != null) return false;
        if(salarioAnual != null) return false;
        if(!modalidadB.isEmpty()) return false;
        return true;
    }
    
}
