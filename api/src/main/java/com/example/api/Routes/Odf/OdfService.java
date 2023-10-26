package com.example.api.Routes.Odf;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * Servicio para la gestión de ODFs.
 */
@AllArgsConstructor
@Service
public class OdfService{
    private OdfRepository odfRepository;

    /**
     * Obtiene todos los ODFs disponibles.
     *
     * @return Una lista de todos los ODFs.
     */
    public List<Odf> getAllOdfs(){
        return odfRepository.findAll();
    }

    /**
     * Obtiene un ODF por su ID.
     *
     * @param id El ID del ODF que se busca.
     * @return Un objeto Optional que contiene el ODF si se encuentra, o un objeto Optional vacío.
     */
    public Optional<Odf> getOdfById(Long id) {
        return odfRepository.findById(id);
        
    }

    /**
     * Actualiza un ODF existente por su ID con los nuevos detalles proporcionados.
     *
     * @param id  El ID del ODF que se desea actualizar.
     * @param odf Los nuevos detalles del ODF.
     * @return El ODF actualizado si existe, de lo contrario, devuelve null.
     */
    public Odf updateOdf(Long id, Odf odf) {
       
        if(odfRepository.existsById(id)){
            return odfRepository.save(odf);
        }
        return null;
    }

    /**
     * Elimina un ODF por su ID.
     *
     * @param id El ID del ODF que se desea eliminar.
     * @return `true` si el ODF se elimina con éxito, de lo contrario, `false`.
     */
    public boolean deleteOdf(Long id) {
        if(odfRepository.existsById(id)){
            odfRepository.deleteById(id);
            return true;
        }
        return false;
    }
}