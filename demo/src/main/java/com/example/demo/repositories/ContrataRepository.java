package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.usuarios.Contrata;

import jakarta.transaction.Transactional;


public interface ContrataRepository extends JpaRepository<Contrata,Long>{
    Contrata findByNombre(String nombre);

    @Modifying 
    @Transactional 
    @Query(value = "DELETE FROM Contrata WHERE id = :id", nativeQuery = true) 
    void deleteById(@Param("id") Long id);
}
