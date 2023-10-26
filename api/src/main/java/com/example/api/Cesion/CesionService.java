package com.example.api.Cesion;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.User.User;
import com.example.api.User.UserRepository;

import lombok.AllArgsConstructor;

/**
 * Servicio que gestiona la creacion y retorno cesiones.
 */
@Service
@AllArgsConstructor
public class CesionService {
    
    private CesionRepository cesionRepository;
    private UserRepository userRepository;
    
    /**
     * Crea un cesion por el user.
     *
     * @param user El user son todos los datos que contiene una cesion
     * @return Una respuesta que debuelve la misma cesion.
     */
    public Cesion saveCesion(User user){
        try {
            LocalDateTime finalyAt = LocalDateTime.now().plusMinutes(60);
            Cesion cesion = Cesion.builder()
                .createdAt(getTimestamp())
                .finalyAt(Timestamp.valueOf(finalyAt))
                .user(user)
                .build();
            cesionRepository.save(cesion);
            return cesion;  
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * Actualiza un cesion por el user.
     *
     * @param user El user son todos los datos que contiene una cesion
     * @return Una respuesta que debuelve la misma cesion.
     */
    public Cesion updateCesion(User user){
        try {
            LocalDateTime finalyAt = LocalDateTime.now().plusMinutes(60);
            Cesion cesion = Cesion.builder()
                .finalyAt(Timestamp.valueOf(finalyAt))
                .user(user)
                .build();
            cesionRepository.save(cesion);
            return cesion;  
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Debulve todas las cesiones que estan activas en ese momento
     *
     * @return List<Cesiones> una lista de todas las cesiones activas.
     */
    public Optional<List<Cesion>> getCesionsActives(){
        return cesionRepository.findByFinalyAtAfter(getTimestamp());
    }

    private Timestamp getTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now);
    }

    /**
     * Devuelve una cesion por el id_persona.
     *
     * @param id_person El id_person de la persona asociada a una persona que asu ves esta
     *  asociada a un usuario que asu ves esta asociada a una cesion.
     * @return Una respuesta que indica si la cesion esta activa o no.
     */
    public ResponseEntity<Boolean> getCesionsActivesByUser(Long id) {
        ResponseEntity<Boolean> response;
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                Optional<Cesion> optionaCesion = cesionRepository.findByFinalyAtAfterAndUser(getTimestamp(), optionalUser.get()); 
                if (optionaCesion.isPresent()) {
                    response =  new ResponseEntity<>(true, HttpStatus.OK);            
                } else {
                    response = new ResponseEntity<>(false, HttpStatus.OK);
                }
            } else {
                response = new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
            
        } catch (Exception e) {
            response =  new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
