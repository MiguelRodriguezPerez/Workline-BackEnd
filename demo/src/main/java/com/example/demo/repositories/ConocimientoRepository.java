package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Conocimiento;

public interface ConocimientoRepository extends JpaRepository<Conocimiento,Long>{
    
}
