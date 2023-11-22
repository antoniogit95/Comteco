package com.example.api.ordenDia.direccion;

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

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ordendia/direccion")
public class DireccionController {
    
    private DireccionService direccionService;

    @GetMapping
    public ResponseEntity<List<Direccion>> getAllDirecciones() {
        List<Direccion> direcciones = direccionService.getAllDirecciones();
        return new ResponseEntity<>(direcciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> getDireccionById(@PathVariable Long id) {
        Direccion direccion = direccionService.getDireccionById(id);
        return direccion != null ? new ResponseEntity<>(direccion, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Direccion> createDireccion(@RequestBody Direccion direccion) {
        Direccion createdDireccion = direccionService.createDireccion(direccion);
        return new ResponseEntity<>(createdDireccion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direccion> updateDireccion(@PathVariable Long id, @RequestBody Direccion direccion) {
        Direccion updatedDireccion = direccionService.updateDireccion(id, direccion);
        return updatedDireccion != null ? new ResponseEntity<>(updatedDireccion, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDireccion(@PathVariable Long id) {
        direccionService.deleteDireccion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
