package com.example.api.Routes.Odf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdfRepository extends JpaRepository<Odf, Long>{


}