package com.example.api.ordenDia.trabajo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajoRespository extends JpaRepository<Trabajo, Long>{
    
} 
