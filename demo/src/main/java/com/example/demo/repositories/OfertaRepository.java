package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.ofertas.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    /*
     * Esta consulta sirve para borrar las ofertas de un Contrata cuando borra su
     * cuenta.
     * No entiendo porque funciona, pero te doy una serie de explicaciones:
     * - La consulta solicita borrar los Busca de la tabla de Busca que estuvieran
     * inscritos en cualquier
     * oferta del contrata que esta borrando su cuenta, pero como el ManyToMany no
     * tiene puesto cascade = CascadeType.REMOVE
     * no borra el busca
     * - Las ofertas se borran porque en la entidad Contrata la colección de ofertas
     * tiene declarado orphanRemoval = true,
     * lo que indica que si el usuario Contrata se borra, también se borren sus
     * ofertas
     */

    @Modifying
    @Transactional
    @Query("DELETE FROM Busca b WHERE b IN (SELECT b2 FROM Busca b2 JOIN b2.listaOfertas o WHERE o.contrata.id = :contrataId)")
    void deleteAllOfertasOfContrata(@Param("contrataId") Long contrataId);

    /*
     * Esta consulta sirve para borrar todas las inscripciones en ofertas de un
     * candidato. Se utiliza cuando borra su cuenta
     * y tampoco entiendo como funciona. Aquí van otras explicaciones
     * - Esta solicitud esta intentando borrar la oferta a la que se inscribio el
     * candidato, pero solo borra la relación
     * entre ambas entidades
     * - Sospecho que lo que esta impidiendo que se borre la oferta también es la
     * relación con la entidad Contrata, la cual
     * únicamente se borraría en el supuesto de que eliminase su cuenta
     */

    @Modifying
    @Transactional
    @Query("DELETE FROM Oferta o WHERE o.id IN (SELECT o2.id FROM Oferta o2 JOIN o2.listaCandidatos b WHERE b.id = :buscaId)")
    void borrarBuscaFromAllOfertas(@Param("buscaId") Long buscaId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Busca b WHERE b IN (SELECT b2 FROM Busca b2 JOIN b2.listaOfertas o WHERE o.id = :ofertaId)")
    void removeAllCandidatesFromOferta(@Param("ofertaId") Long ofertaId);

}
