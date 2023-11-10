package com.example.api.Routes.Fdt;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/**
 * Controlador REST para la gestión de FDTs.
 */
@RestController
@RequestMapping("/api/v1/fdt")
@CrossOrigin("*")
@AllArgsConstructor
public class FdtController{

    private FdtService fdtService;

    /**
     * Obtiene todos los FDTs disponibles.
     *
     * @return Una lista de todos los FDTs.
     */
    @GetMapping
    public ResponseEntity<List<Fdt>> getAllFtp() {
        List<Fdt> ftps = fdtService.getAllFdts();
        return new ResponseEntity<>(ftps, HttpStatus.OK);
    }

    /**
     * Obtiene todos los FDTs asociados a un ODF específico por su ID.
     *
     * @param id_odf El ID del ODF al que se asocian los FDTs.
     * @return Una lista de FDTs asociados al ODF.
     */
    @GetMapping("/byodf/{id_odf}")
    public ResponseEntity<List<Fdt>> getAllFdtByOdf(@PathVariable Long id_odf){
        System.out.println("aciendo pruebas: "+id_odf);
        return fdtService.getAllFdtsByOdf(id_odf);
    }

    /**
     * Actualiza un FDT existente por su ID con los nuevos detalles proporcionados.
     *
     * @param id  El ID del FDT que se desea actualizar.
     * @param fdt Los nuevos detalles del FDT.
     * @return El FDT actualizado si existe, de lo contrario, devuelve null.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Fdt> updateFdt(@PathVariable Long id, @RequestBody Fdt fdt) {
        Fdt updatedFdt = fdtService.updateFdt(id, fdt);
        if (updatedFdt != null) {
            return new ResponseEntity<>(updatedFdt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un FDT por su ID.
     *
     * @param id El ID del FDT que se desea eliminar.
     * @return `true` si el FDT se elimina con éxito, de lo contrario, `false`.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteFdt(@PathVariable Long id) {
        boolean deleted = fdtService.deleteFdt(id);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea un nuevo FDT con los detalles proporcionados.
     *
     * @param fdt El FDT que se desea crear.
     * @return El FDT creado con su ID asignado.
     */
    @PostMapping
    public ResponseEntity<Fdt> createFdt(@RequestBody Long id_odf, @RequestBody Fdt fdt) {
        Fdt createdFdt = fdtService.createFdt(id_odf, fdt);
        if (createdFdt != null) {
            return new ResponseEntity<>(createdFdt, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getzone/{codFDT}")
    public ResponseEntity<String> getZoneODF(@PathVariable String codFDT){
        codFDT = codFDT.toUpperCase();
        return fdtService.getZoneODFByCodFDT(codFDT);
    }
}