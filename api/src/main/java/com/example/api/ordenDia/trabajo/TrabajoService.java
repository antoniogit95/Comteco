package com.example.api.ordenDia.trabajo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrabajoService {

    private TrabajoRespository trabajoRespository;

    public ResponseEntity<List<Trabajo>> getAllTrabajos() {
        try {
            List<Trabajo> trabajos = trabajoRespository.findAll();
            return new ResponseEntity<>(trabajos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    public ResponseEntity<Trabajo> getTrabajoById(Long id) {
        try {
            Optional<Trabajo> tOptional = trabajoRespository.findById(id);
            return new ResponseEntity<>(tOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    public ResponseEntity<Trabajo> createTrabajo(Trabajo trabajo) {
        try {
            trabajoRespository.save(trabajo);
            return new ResponseEntity<>(trabajo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Trabajo> updateTrabajo(Long id, Trabajo trabajo) {
        try {
            Optional<Trabajo> tOptional = trabajoRespository.findById(id);
            if (tOptional.isPresent()) {
                trabajo.setId(id);
                trabajoRespository.save(trabajo);
                return new ResponseEntity<>(trabajo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(trabajo, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Boolean> deleteTrabajo(Long id) {
        try {
            trabajoRespository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
    
}
