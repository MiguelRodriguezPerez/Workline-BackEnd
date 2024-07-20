package com.example.demo.services.ofertas;

import org.springframework.stereotype.Service;

import com.example.demo.domain.ofertas.BusquedaOferta;

@Service
public class BusquedaOfertaServiceImpl implements BusquedaOfertaService{

    @Override
    public BusquedaOferta obtenerBusquedaDesdeUrl(String url) {
        System.out.println(url + "ESTO ES LO QUE LLEGA");
        return null;
    }
    
}
