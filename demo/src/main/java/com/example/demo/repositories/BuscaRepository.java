package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.usuarios.buscaData.Busca;

public interface BuscaRepository extends JpaRepository<Busca,Long>{
    Busca findByNombre(String nombre);
}
