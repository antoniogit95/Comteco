package com.example.api.ordenDia.direccion;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DireccionService {
    
    private DireccionRepository direccionRepository;


    public List<Direccion> getAllDirecciones() {
        return direccionRepository.findAll();
    }

    public Direccion getDireccionById(Long id) {
        return direccionRepository.findById(id).orElse(null);
    }

    public Direccion createDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    public Direccion updateDireccion(Long id, Direccion direccion) {
        direccion.setId(id);
        return direccionRepository.save(direccion);
    }

    public void deleteDireccion(Long id) {
        direccionRepository.deleteById(id);
    }

}
