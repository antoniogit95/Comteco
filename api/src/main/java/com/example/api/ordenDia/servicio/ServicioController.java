package com.example.api.ordenDia.servicio;

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
@RequestMapping("/api/v1/ordendia/servicio")
@AllArgsConstructor
public class ServicioController {

    private ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<Servicio>> getAllServicios() {
        try {
            List<Servicio> servicios = servicioService.getAllServicios();
            return new ResponseEntity<>(servicios, HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Long id) {
        try {
            Servicio servicio = servicioService.getServicioById(id);
            return new ResponseEntity<>(servicio, HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping
    public ResponseEntity<Servicio> createServicio(@RequestBody Servicio servicio) {
        try {
            Servicio createdServicio = servicioService.createServicio(servicio);
            return new ResponseEntity<>(createdServicio, HttpStatus.CREATED);    
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable Long id, @RequestBody Servicio servicio) {
        try {
            Servicio updatedServicio = servicioService.updateServicio(id, servicio);
            return new ResponseEntity<>(updatedServicio, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteServicio(@PathVariable Long id) {
        Boolean deleteService = servicioService.deleteServicio(id);
        return deleteService? new ResponseEntity<>(true, HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
