package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Experiencia;
import com.example.demo.domain.usuarios.Busca;

public interface ExperienciaRepository extends JpaRepository<Experiencia,Long> {
    void deleteAllByBusca(Busca busca);
}
