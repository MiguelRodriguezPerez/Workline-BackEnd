package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.usuarios.busca.Busca;

import jakarta.transaction.Transactional;

public interface BuscaRepository extends JpaRepository<Busca,Long>{
    Busca findByNombre(String nombre);


    /*Por razones relacionadas con las transacciones de mysql no puedes borrar
    un busca por id mediante los métodos por palabras del jpa repository, a pesar
    de que se hayan borrado el resto de relaciones con las entidades. 

    Tienes que hacerlo mediante una consulta mysql*/
    @Modifying 
    @Transactional 
    @Query(value = "DELETE FROM Busca WHERE id = :id", nativeQuery = true) 
    void deleteById(@Param("id") Long id);
}
