package com.example.api.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que define un repositorio para la entidad 'Person'.
 * Esta interfaz extiende JpaRepository para proporcionar operaciones de acceso a datos de 'Person'.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
