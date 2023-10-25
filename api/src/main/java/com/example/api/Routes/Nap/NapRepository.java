package com.example.api.Routes.Nap;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.Routes.Fdt.Fdt;

@Repository
public interface NapRepository extends JpaRepository<Nap, Long> {

    Optional<Nap> findByCod(String string);
    Optional<Nap> findByCodAndFdt(String string, Fdt fdt);
    List<Nap> findAllByFdt(Fdt fdt);
    
}
