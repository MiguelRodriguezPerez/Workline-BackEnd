package com.example.demo.services.ofertas;

import com.example.demo.domain.ofertas.BusquedaOferta;

public interface BusquedaOfertaService {
    BusquedaOferta obtenerBusquedaDesdeUrl(String url);
}
