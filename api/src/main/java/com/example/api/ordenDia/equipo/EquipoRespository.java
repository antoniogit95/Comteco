package com.example.api.ordenDia.equipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRespository extends JpaRepository<Equipo, Long>{
   
}
