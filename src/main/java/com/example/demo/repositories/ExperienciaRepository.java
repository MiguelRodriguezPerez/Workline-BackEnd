package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Experiencia;

public interface ExperienciaRepository extends JpaRepository<Experiencia,Long> {

    @Modifying 
    @Transactional 
    @Query(value = "DELETE FROM experiencia WHERE id = :id", nativeQuery = true) 
    void deleteById(@Param("id") Long id);

    @Modifying 
    @Transactional 
    @Query(value = "DELETE FROM experiencia WHERE busca_id = :buscaId", nativeQuery = true) 
    void deleteAllExperienciaByBuscaId(@Param("buscaId") Long buscaId);


}
