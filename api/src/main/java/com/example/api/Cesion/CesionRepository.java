package com.example.api.Cesion;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.User.User;

/**
 * Interfaz que define un repositorio para la entidad 'Cesion'.
 * Esta interfaz extiende JpaRepository para proporcionar operaciones de acceso a datos de 'Cesion'.
 */
@Repository
public interface CesionRepository extends JpaRepository<Cesion, Long>{
    
    /**
     * Busca una Lista de cesiones  por el timepo actual.
     *
     * @param now La entidad Timestamp asociada al tiempo actual.
     * @return Un objeto Optional que contiene la lista de todos las cesiones activas.
     */
    Optional<List<Cesion>> findByFinalyAtAfter(Timestamp now);

    /**
     * Busca una Cesiones  por el timepo actual.
     *
     * @param now La entidad Timestamp asociada al tiempo actual.
     * @param user la entidad a ser buscada asociada a un usuario
     * @return Un objeto Optional que contiene la cesion si esta activa.
     */
    Optional<Cesion> findByFinalyAtAfterAndUser(Timestamp now, User user);

    /**
     * Busca todas las Cesiones  de un usuario determinado.
     *
     * @param user la entidad a ser buscada asociada a un usuario
     * @return Un objeto Optional que contiene una lista de todas las cesiones un un usuario
     */
    Optional<List<Cesion>> findAllByUser(User user);
    
}
