package com.example.api.Routes.Fdt;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.Routes.Odf.Odf;

/**
 * Repositorio de datos para la entidad FDT (Fiber Distribution Terminal).
 */
@Repository
public interface FdtRepository extends JpaRepository<Fdt, Long> {
    
    /**
     * Busca un FDT por su código único.
     *
     * @param cod El código único del FDT que se busca.
     * @return Un objeto Optional que contiene el FDT si se encuentra, o un objeto Optional vacío.
     */
    Optional<Fdt> findByCod(String cod);

    /**
     * Obtiene una lista de todos los FDTs asociados a un ODF específico.
     *
     * @param odf El ODF al que se asocian los FDTs.
     * @return Una lista de FDTs asociados al ODF.
     */
    List<Fdt> findAllByOdf(Odf odf);
}
