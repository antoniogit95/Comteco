package com.example.api.Routes.Nap;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controlador REST para gestion de NAPs
 */
@RestController
@RequestMapping("/api/v1/nap")
@CrossOrigin("*")
@AllArgsConstructor
public class NapController {
    
    private NapService napService;

    /**
     * Otiene Todas Entidades NAPs
     * 
     * @return una lista de todas las NAPs
     */
    @GetMapping()
    public ResponseEntity<List<Nap>> getAllNaps() {
        List<Nap> naps = napService.getAllNaps();
        return new ResponseEntity<>(naps, HttpStatus.OK);
    }
    
    /**
     * Obtener Todas Las Entidades NAPs asocidadas a un FDT por su ID
     * 
     * @param id_fdt el Id del FDT al que se asocia a la NAP
     * @return una lista de todas las NAPs asociadas al FDT
     */
    @GetMapping("/byfdt/{id_fdt}")
    public ResponseEntity<List<Nap>> getAllNapsByOdf(@PathVariable Long id_fdt){
        return napService.getAllNapsByOdf(id_fdt);
    }

    /**
     * Actualiza una NAP existente por su ID con los nuevos detalles proporcionados.
     *
     * @param id  El ID del NAP que se desea actualizar.
     * @param naps Los nuevos detalles de la NAP.
     * @return La NAP actualizado si existe, de lo contrario, devuelve null.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Nap> updateNap(@PathVariable Long id, @RequestBody Nap nap) {
        Nap updatedNap = napService.updateNap(id, nap);
        if (updatedNap != null) {
            return new ResponseEntity<>(updatedNap, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una NAP por su ID.
     *
     * @param id El ID de la NAP que se desea eliminar.
     * @return 'true' si la NAP se elimina con éxito, de lo contrario, 'false'.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteNap(@PathVariable Long id) {
        boolean deleted = napService.deleteNap(id);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea un nuevo NAP con los detalles proporcionados.
     *
     * @param nap El NAP que se desea crear.
     * @param id_fdt ID del FDt que se desea asignar la nap.
     * @return El NAP creado con su ID asignado.
     */
    @PostMapping
    public ResponseEntity<Nap> createNap(@RequestBody Long id_fdt, @RequestBody Nap nap) {
        Nap createdNap = napService.createNap(id_fdt, nap);
        if (createdNap != null) {
            return new ResponseEntity<>(createdNap, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
