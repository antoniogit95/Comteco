package com.example.api.ordenDia.ubicacionTecnica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionTecnicaRepository extends JpaRepository<UbicacionTecnica, Long>{
    
}
