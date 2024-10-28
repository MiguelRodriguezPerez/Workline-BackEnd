package com.example.demo.domain.entidadesApi;

import com.example.demo.domain.ofertas.BusquedaOferta;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaginaJobSearchRequest {
    private int pagina;
    private BusquedaOferta busquedaOferta;
}
