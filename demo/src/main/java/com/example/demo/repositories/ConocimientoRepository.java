package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Conocimiento;
import com.example.demo.domain.usuarios.Busca;

public interface ConocimientoRepository extends JpaRepository<Conocimiento,Long>{
    void deleteAllByBusca(Busca busca);
}
