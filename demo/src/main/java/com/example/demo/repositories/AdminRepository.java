package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.usuarios.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{
    
}
