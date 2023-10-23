package com.example.api.Routes.Fdt;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FdtRepository extends JpaRepository<Fdt, Long> {
    
    Optional<Fdt> findByCod(String cod);
}
