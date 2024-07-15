package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.usuarios.Contrata;


public interface ContrataRepository extends JpaRepository<Contrata,Long>{
    Contrata findByNombre(String nombre);
}
