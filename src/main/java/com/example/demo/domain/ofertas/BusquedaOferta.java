package com.example.demo.domain.ofertas;

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

    @Nullable
    private String puesto;
    @Nullable
    private TipoContrato tipoContrato;
    @Nullable
    private String ciudad;
    // @Nullable
    // private List<String> requisitos;
    @Nullable
    private Double salarioAnualMinimo;
    @Nullable
    private ModalidadTrabajo modalidadTrabajo;

}
