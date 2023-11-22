package com.example.api.ordenDia.ubicacionTecnica;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Service
public class UbicacionTecnicaService {

    private UbicacionTecnicaRepository ubicacionTecnicaRepository;

    public List<UbicacionTecnica> getAllUbicacionesTecnicas() {
        return ubicacionTecnicaRepository.findAll();
    }

    public UbicacionTecnica getUbicacionTecnicaById(Long id) {
        return ubicacionTecnicaRepository.findById(id).orElse(null);
    }

    public UbicacionTecnica createUbicacionTecnica(UbicacionTecnica ubicacionTecnica) {
        return ubicacionTecnicaRepository.save(ubicacionTecnica);
    }

    public UbicacionTecnica updateUbicacionTecnica(Long id, UbicacionTecnica ubicacionTecnica) {
        ubicacionTecnica.setId(id);
        return ubicacionTecnicaRepository.save(ubicacionTecnica);
    }

    public void deleteUbicacionTecnica(Long id) {
        ubicacionTecnicaRepository.deleteById(id);
    }
}
