package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.ofertas.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    /* MySql no te deja usar correctamente los métodos por palabras del jpaRepository
    (problemas relacionados con la transacción en la base de datos)*/

    /* ------------------------- Querys relacionadas con la entidad contrata ------------------------------- */

    /*Este método sirve para borrar todas las inscripciones de todas las ofertas de un contrata determinado.
    Es la primera query que se ejecuta cuando un contrata borra su cuenta*/
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM busca_oferta WHERE oferta_id IN ("
            + "SELECT o.id FROM ofertas o WHERE o.contrata_id = :contrataId)", nativeQuery = true)
    void deleteAllCandidatosFromContrataId(@Param("contrataId") Long contrataId);

    /* Este método borra todas las ofertas de un contrata. Para que funcione
    correctamente es necesario que antes se ejecute bien el anterior método. Si no, no funcionara

    Esta es la segunda query que se ejecuta cuando un contrata borra su cuenta*/

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ofertas WHERE contrata_id = :contrataId", nativeQuery = true)
    void deleteAllOfertasByContrataId(@Param("contrataId") Long contrataId);

    /*Este método sirve para borrar la relación que tengan todos los busca con una
    oferta determinada. Es el primer método que se ejecuta cuando un contrata quiere
    borrar una oferta*/
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM busca_oferta WHERE oferta_id = :ofertaId", nativeQuery = true)
    void removeAllCandidatesFromOferta(@Param("ofertaId") Long ofertaId);

    /*Para borrar una entidad a través de mysql siempre SIEMPRE tienes que borrarla por id
    a través de una consulta mysql.
    
    Si no ejecutas este método al borrar una oferta se quedará guardada sin tener relaciones con
    otra entidad, y por la razón que sea orphanRemoval = true no va aquí (No funciono con busca y sus
    conocimientos y experiencias)*/
    @Modifying 
    @Transactional 
    @Query(value = "DELETE FROM ofertas WHERE id = :id", nativeQuery = true) 
    void deleteById(@Param("id") Long id);

    /*------------------------- Querys relacionadas con la entidad busca -------------------------------*/

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM busca_oferta WHERE busca_id = :buscaId", nativeQuery = true)
    void deleteBuscaFromAllOfertas(@Param("buscaId") Long buscaId);


}
