package com.example.api.ordenDia.solicitud;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ordendia/solicitud")
public class SolicitudController {

    private SolicitudService solicitudService;

    @GetMapping
    public ResponseEntity<List<Solicitud>> getAllSolicitudes() {
        return solicitudService.getAllSolicitudes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getSolicitudById(@PathVariable Long id) {
        return solicitudService.getSolicitudById(id);
    }

    @PostMapping
    public ResponseEntity<Solicitud> createSolicitud(@RequestBody Solicitud solicitud) {
        return solicitudService.createSolicitud(solicitud);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> updateSolicitud(@PathVariable Long id, @RequestBody Solicitud solicitud) {
        return solicitudService.updateSolicitud(id, solicitud);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSolicitud(@PathVariable Long id) {
        return solicitudService.deleteSolicitud(id);
    }
}