package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Experiencia;

public interface ExperienciaRepository extends JpaRepository<Experiencia,Long> {
    
}
