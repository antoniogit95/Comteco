package com.example.api.Routes.Fdt;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.Routes.Odf.Odf;

@Repository
public interface FdtRepository extends JpaRepository<Fdt, Long> {
    
    Optional<Fdt> findByCod(String cod);

    List<Fdt> findAllByOdf(Odf odf);
}
