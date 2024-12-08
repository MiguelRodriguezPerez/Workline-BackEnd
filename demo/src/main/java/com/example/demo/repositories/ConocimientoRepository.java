package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Conocimiento;

public interface ConocimientoRepository extends JpaRepository<Conocimiento,Long>{

    @Modifying 
    @Transactional 
    @Query(value = "DELETE FROM conocimiento WHERE id = :id", nativeQuery = true) 
    void deleteById(@Param("id") Long id);

    @Modifying 
    @Transactional 
    @Query(value = "DELETE FROM conocimiento WHERE busca_id = :buscaId", nativeQuery = true) 
    void deleteAllConocimientoFromBuscaId(@Param("buscaId") Long buscaId);
}
