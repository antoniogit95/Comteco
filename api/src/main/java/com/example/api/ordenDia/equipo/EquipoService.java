package com.example.api.ordenDia.equipo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EquipoService {

    private EquipoRespository equipoRespository;

    public ResponseEntity<List<Equipo>> getAllEquipos() {
        try {
            List<Equipo> equipos = equipoRespository.findAll();
            return new ResponseEntity<>(equipos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    public ResponseEntity<Equipo> getEquipoById(Long id) {
        try {
            Optional<Equipo> eqOptional = equipoRespository.findById(id);
            return new ResponseEntity<>(eqOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Equipo> createEquipo(Equipo equipo) {
        try {
            equipoRespository.save(equipo);
            return new ResponseEntity<>(equipo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }    
    }

    public ResponseEntity<Equipo> updateEquipo(Long id, Equipo equipo) {
        try {
            Optional<Equipo> eOptional = equipoRespository.findById(id);
            if(eOptional.isPresent()){
                equipo.setId(id);
                equipoRespository.save(equipo);
                return new ResponseEntity<>(equipo, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(equipo, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Boolean> deleteEquipo(Long id) {
        try {
            equipoRespository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
    
}
