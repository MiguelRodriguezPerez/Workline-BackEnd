package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.usuarios.contrata.Contrata;

import jakarta.transaction.Transactional;


public interface ContrataRepository extends JpaRepository<Contrata,Long>{
    Contrata findByNombre(String nombre);

    /*Por razones relacionadas con las transacciones de mysql no puedes borrar
    un contrata por id mediante los métodos por palabras del jpa repository, a pesar
    de que se hayan borrado el resto de relaciones con las entidades. 

    Tienes que hacerlo mediante una consulta mysql*/
    @Modifying 
    @Transactional 
    @Query(value = "DELETE FROM Contrata WHERE id = :id", nativeQuery = true) 
    void deleteById(@Param("id") Long id);
}
