package com.example.api.Register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataTecnicoRepository extends JpaRepository<DataTecnico, Long>{
    
}
