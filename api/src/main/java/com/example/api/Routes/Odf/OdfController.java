package com.example.api.Routes.Odf;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;

/**
 * Controlador REST para la gestión de ODFs 
 */
@RequestMapping("/api/v1/odf")
@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class OdfController{

    private OdfService odfService;

    /**
     * Obtiene todos los ODFs disponibles.
     *
     * @return Una lista de todos los ODFs.
     */
    @GetMapping
    public ResponseEntity<List<Odf>> getAllOdf() {
        List<Odf> Odfs = odfService.getAllOdfs();
        return new ResponseEntity<>(Odfs, HttpStatus.OK);
    }

    /**
     * Obtiene un ODF por su ID.
     *
     * @param id El ID del ODF que se busca.
     * @return El ODF si se encuentra, de lo contrario, devuelve null.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Odf> getOdfById(@PathVariable Long id) {
        Optional<Odf> optionalOdf = odfService.getOdfById(id);
        if(optionalOdf.isPresent()){
            return new ResponseEntity<>(optionalOdf.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza un ODF existente por su ID con los nuevos detalles proporcionados.
     *
     * @param id  El ID del ODF que se desea actualizar.
     * @param odf Los nuevos detalles del ODF.
     * @return El ODF actualizado si existe, de lo contrario, devuelve null.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Odf> updateOdf(@PathVariable Long id, @RequestBody Odf odf) {
        Odf updatedOdf = odfService.updateOdf(id, odf);
        if (updatedOdf != null) {
            return new ResponseEntity<>(updatedOdf, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un ODF por su ID.
     *
     * @param id El ID del ODF que se desea eliminar.
     * @return `true` si el ODF se elimina con éxito, de lo contrario, `false`.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOdf(@PathVariable Long id) {
        boolean deleted = odfService.deleteOdf(id);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}