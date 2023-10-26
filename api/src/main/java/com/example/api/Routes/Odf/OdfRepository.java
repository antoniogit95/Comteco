package com.example.api.Routes.Odf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que define un repositorio para la entidad 'Odf'.
 */
@Repository
public interface OdfRepository extends JpaRepository<Odf, Long>{


}