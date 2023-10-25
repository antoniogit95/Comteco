package com.example.api.Routes.Pos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.Routes.Nap.Nap;

@Repository
public interface PosRepository extends JpaRepository<Pos, Long>{

    Optional<Pos> findByCodAndNap(String string, Nap nap);

    List<Pos> findAllByNap(Nap nap);
    
}
