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

@Service
@AllArgsConstructor
public class CesionService {
    
    private CesionRepository cesionRepository;
    private UserRepository userRepository;
    
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

    public Optional<List<Cesion>> getCesionsActives(){
        return cesionRepository.findByFinalyAtAfter(getTimestamp());
    }

    private Timestamp getTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now);
    }

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
