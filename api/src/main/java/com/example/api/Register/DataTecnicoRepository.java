package com.example.api.Register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que define un repositorio para la entidad 'DataTecnico'.
 * Esta interfaz extiende JpaRepository para proporcionar operaciones de acceso a datos de 'DataTecnico'.
 */
@Repository
public interface DataTecnicoRepository extends JpaRepository<DataTecnico, Long>{
    
}
