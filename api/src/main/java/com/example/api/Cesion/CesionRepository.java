package com.example.api.Cesion;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.User.User;


@Repository
public interface CesionRepository extends JpaRepository<Cesion, Long>{
    
    Optional<List<Cesion>> findByFinalyAtAfter(Timestamp now);

    Optional<Cesion> findByFinalyAtAfterAndUser(Timestamp now, User user);
    
}
