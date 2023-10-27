package com.example.api.Routes.Nap;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.Routes.Fdt.Fdt;

/**
 * Repositorio para los datos para la entidad NAP
 */
@Repository
public interface NapRepository extends JpaRepository<Nap, Long> {

    /**
     * Busca una NAP por su Cod
     * @param string el codigo que se busca
     * @return Un objeto Optional que contiene el NAP si se encuentra, o un objeto Optional vacío.
     */
    Optional<Nap> findByCod(String string);

    /**
     * Busca una NAP por su Cod y ClasFDT
     * @param string el codigo que se busca
     * @param fdt la clase FDT que esta asociada a la NAP
     * @return Un objeto Optional que contiene el Nap si se encuentra, o un objeto Optional vacio.
     */
    Optional<Nap> findByCodAndFdt(String string, Fdt fdt);

    /**
     * Obtiene una lista de todas las NAPs asociadas a un FDT
     * @param fdt el FDT al que se asocia a una NAP 
     * @return una lista de todas las NAPs asociadas a la FDT
     */
    List<Nap> findAllByFdt(Fdt fdt);
    
}
