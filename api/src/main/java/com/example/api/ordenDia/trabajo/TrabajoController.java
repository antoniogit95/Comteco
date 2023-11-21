package com.example.api.ordenDia.trabajo;

import java.util.List;

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

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/ordendia/trabajo")
public class TrabajoController {

    private TrabajoService trabajoService;
    
    @GetMapping
    public ResponseEntity<List<Trabajo>> getAllTrabajos() {
        return trabajoService.getAllTrabajos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trabajo> getTrabajoById(@PathVariable Long id) {
        return trabajoService.getTrabajoById(id);
    }

    @PostMapping
    public ResponseEntity<Trabajo> createTrabajo(@RequestBody Trabajo trabajo) {
        return trabajoService.createTrabajo(trabajo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trabajo> updateTrabajo(@PathVariable Long id, @RequestBody Trabajo trabajo) {
        return trabajoService.updateTrabajo(id, trabajo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTrabajo(@PathVariable Long id) {
        return trabajoService.deleteTrabajo(id);
    }
}
