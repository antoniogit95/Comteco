package com.example.api.Routes.Nap;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.Routes.Fdt.Fdt;
import com.example.api.Routes.Fdt.FdtService;

import lombok.AllArgsConstructor;

/**
 * Servicio para la gestion de NAPs
 */
@Service
@AllArgsConstructor
public class NapService {

    private NapRepository napRepository;
    private FdtService fdtService;

    /**
     * Obtiene todas las NAPs disponibles.
     * 
     * @return Una lista de todas las NAPs.
     */
    public List<Nap> getAllNaps() {
        return napRepository.findAll();
    }

    /**
     * Obtiene una NAP por su ID
     * 
     * @param id el ID de la NAP que se busca
     * @return un objeto Optional que contiene una NAP si se encuentra, o un objeto Optional vacio 
     */
    public Optional<Nap> getNapById(Long id){
        return napRepository.findById(id);
    }

    /**
     * Obtiene todas las NAPs asociados a un fDT específico por su ID.
     *
     * @param id_fdt El ID del FDT al que se asocian las NAPs.
     * @return Una lista de las NAPs asociados al FDT.
     */
    public ResponseEntity<List<Nap>> getAllNapsByOdf(Long id_fdt) {
        try {
            Optional<Fdt> optionalFdt = fdtService.getFdtById(id_fdt);
            List<Nap> naps = napRepository.findAllByFdt(optionalFdt.get());
            return new ResponseEntity<>(naps, HttpStatus.OK); 
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Actualiza una NAP existente por su ID con los nuevos detalles proporcionados.
     *
     * @param id  El ID de la NAP que se desea actualizar.
     * @param fdt Los nuevos detalles de la NAP.
     * @return La NAP actualizado si existe, de lo contrario, devuelve null.
     */
    public Nap updateNap(Long id, Nap nap) {
        if (napRepository.existsById(id)) {
            return napRepository.save(nap);
        }
        return null;
    }

    /**
     * Elimina una NAP por su ID.
     *
     * @param id El ID de la NAP que se desea eliminar.
     * @return 'true' si la NAP se elimina con éxito, de lo contrario, 'false'.
     */
    public boolean deleteNap(Long id) {
        if (napRepository.existsById(id)) {
            napRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Crear una NAP.
     *
     * @param id El ID del FDT que se desa hacer referencia, solo en caso del NAP no tener id_fdt.
     * @return el mismo obgeto NAP, de lo contrario, 'NULL'.
     */
    public Nap createNap(Long id_fdt, Nap nap) {
        try {
            return napRepository.save(nap);
        } catch (Exception e) {
            return null;
        }
    }
    
}
