package com.example.api.ordenDia.PlanComercial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanComercialRepository extends JpaRepository<PlanComercial, Long>{
    
}
