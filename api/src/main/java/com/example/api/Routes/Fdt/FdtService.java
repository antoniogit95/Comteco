package com.example.api.Routes.Fdt;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.Routes.Odf.Odf;
import com.example.api.Routes.Odf.OdfService;

import lombok.AllArgsConstructor;

/**
 * Servicio para la gestión de FDTs.
 */
@Service
@AllArgsConstructor
public class FdtService {

    private FdtRepository fdtRepository;
    private OdfService odfService;

    /**
     * Obtiene todos los FDTs disponibles.
     *
     * @return Una lista de todos los FDTs.
     */
    public List<Fdt> getAllFdts() {
        return fdtRepository.findAll();
    }

    /**
     * Obtiene un FDT por su ID.
     *
     * @param id El ID del FDT que se busca.
     * @return Un objeto Optional que contiene el FDT si se encuentra, o un objeto Optional vacío.
     */
    public Optional<Fdt> getFdtById(Long id_fdt){
        return fdtRepository.findById(id_fdt);
    }

    /**
     * Obtiene todos los FDTs asociados a un ODF específico por su ID.
     *
     * @param id_odf El ID del ODF al que se asocian los FDTs.
     * @return Una lista de FDTs asociados al ODF.
     */
    public ResponseEntity<List<Fdt>> getAllFdtsByOdf(Long id_odf) {
        try {
            Optional<Odf> optionalOdf = odfService.getOdfById(id_odf);
            List<Fdt> fdts = fdtRepository.findAllByOdf(optionalOdf.get());
            return new ResponseEntity<>(fdts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza un FDT existente por su ID con los nuevos detalles proporcionados.
     *
     * @param id  El ID del ODF que se desea actualizar.
     * @param fdt Los nuevos detalles del FDT.
     * @return El FDT actualizado si existe, de lo contrario, devuelve null.
     */
    public Fdt updateFdt(Long id, Fdt fdt) {
        if(fdtRepository.existsById(id)){
            return fdtRepository.save(fdt);
        }
        return null;
    }

    /**
     * Elimina un FDT por su ID.
     *
     * @param id El ID del FDT que se desea eliminar.
     * @return 'true' si el FDT se elimina con éxito, de lo contrario, 'false'.
     */
    public boolean deleteFdt(Long id) {
        if (fdtRepository.existsById(id)) {
            fdtRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Crear un FDT.
     *
     * @param id El ID del ODF que se desa hacer referencia, solo en caso del FDT no tener id_odf.
     * @return el mismo obgeto FDT, de lo contrario, 'NULL'.
     */
    public Fdt createFdt(Long id_odf, Fdt fdt) {
        try {
            return fdtRepository.save(fdt);
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<String> getZoneODFByCodFDT(String codFDT) {

        System.out.println(codFDT+" ----------------------------------------- codifo FDT");
        Optional<Fdt> optionalFDT = fdtRepository.findByCod(codFDT);
        if(optionalFDT.isPresent()){
            System.out.println("se Encontro el FDT: "+optionalFDT.get().getCod());
            Odf odf = optionalFDT.get().getOdf();
            return new ResponseEntity<>(odf.getNombre() + "-", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sin Nombre", HttpStatus.BAD_REQUEST);
    }

}
