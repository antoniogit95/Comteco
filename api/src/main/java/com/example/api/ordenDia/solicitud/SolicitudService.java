package com.example.api.ordenDia.solicitud;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitudService {

    private SolicitudRepository solicitudRepository;

    public ResponseEntity<List<Solicitud>> getAllSolicitudes() {
        try {
            List<Solicitud> solicituds = solicitudRepository.findAll();
            return new ResponseEntity<>(solicituds, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Solicitud> getSolicitudById(Long id) {
        try {
            Optional<Solicitud> sOptional = solicitudRepository.findById(id);
            return new ResponseEntity<>(sOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Solicitud> createSolicitud(Solicitud solicitud) {
        try {
            Solicitud response = solicitudRepository.save(solicitud);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Solicitud> updateSolicitud(Long id, Solicitud solicitud) {
        try {
            Optional<Solicitud> sOptional = solicitudRepository.findById(id);
            if(sOptional.isPresent()){
                solicitud.setId(id);
                solicitudRepository.save(solicitud);
                return new ResponseEntity<>(solicitud, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(solicitud, HttpStatus.FAILED_DEPENDENCY);
            }
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Boolean> deleteSolicitud(Long id) {
        try {
            solicitudRepository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
}
