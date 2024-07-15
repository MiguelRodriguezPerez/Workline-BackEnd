package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.ofertas.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta,Long>{

}
