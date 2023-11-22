package com.example.api.ordenDia.ubicacionTecnica;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ordendia/ubicacion-tecnica")
public class UbicacionTecnicaController {
 
    private UbicacionTecnicaService ubicacionTecnicaService;
    
    @GetMapping
    public ResponseEntity<List<UbicacionTecnica>> getAllUbicacionesTecnicas() {
        List<UbicacionTecnica> ubicacionesTecnicas = ubicacionTecnicaService.getAllUbicacionesTecnicas();
        return new ResponseEntity<>(ubicacionesTecnicas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionTecnica> getUbicacionTecnicaById(@PathVariable Long id) {
        UbicacionTecnica ubicacionTecnica = ubicacionTecnicaService.getUbicacionTecnicaById(id);
        return ubicacionTecnica != null ? new ResponseEntity<>(ubicacionTecnica, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<UbicacionTecnica> createUbicacionTecnica(@RequestBody UbicacionTecnica ubicacionTecnica) {
        UbicacionTecnica createdUbicacionTecnica = ubicacionTecnicaService.createUbicacionTecnica(ubicacionTecnica);
        return new ResponseEntity<>(createdUbicacionTecnica, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionTecnica> updateUbicacionTecnica(@PathVariable Long id, @RequestBody UbicacionTecnica ubicacionTecnica) {
        UbicacionTecnica updatedUbicacionTecnica = ubicacionTecnicaService.updateUbicacionTecnica(id, ubicacionTecnica);
        return updatedUbicacionTecnica != null ? new ResponseEntity<>(updatedUbicacionTecnica, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacionTecnica(@PathVariable Long id) {
        ubicacionTecnicaService.deleteUbicacionTecnica(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
