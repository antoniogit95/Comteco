package com.example.api.Register;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

/**
 * Controlador REST para la gestión de datos técnicos.
 * En la siguiente ruta /api/v1/datatecnico
 */
@RestController
@RequestMapping("/api/v1/datatecnico")
@CrossOrigin("*")
@AllArgsConstructor
public class DataTecnicoController {
    
    private DataTecnicoService dataTecnicoService;

    /**
     * Obtiene datos técnicos por su ID.
     *
     * @param id El ID de los datos técnicos que se buscan.
     * @return Los datos técnicos si se encuentran, de lo contrario, devuelve NOT FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DataTecnico> getDataTecnicoById(@PathVariable Long id){
        DataTecnico dataTecnico = dataTecnicoService.getDataTecnicoById(id);
        if(dataTecnico != null){
            return new ResponseEntity<>(dataTecnico, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea nuevos datos técnicos con los detalles proporcionados en la clase DataTecnicoRequest.
     *
     * @param request Los detalles de los datos técnicos a crear.
     * @return Los datos técnicos creados y un estado OK.
     */
    @PostMapping
    public ResponseEntity<DataTecnico> createDataTecnico(@RequestBody DataTecnicoRequest request){
        return ResponseEntity.ok(dataTecnicoService.createRegisterDataTecnico(request));
    }

    /**
     * Obtiene todos los datos técnicos disponibles.
     *
     * @return Una lista de todos los datos técnicos y un estado OK, de lo contrario devuelve un NOT FOUND
     */
    @GetMapping
    public ResponseEntity<List<DataTecnico>> getAllDataTecnicos() {
        try {
            List<DataTecnico> dataTecnicos = dataTecnicoService.getAllDataTecnicos();
            return new ResponseEntity<>(dataTecnicos, HttpStatus.OK);  
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina datos técnicos por su ID.
     *
     * @param id El ID de los datos técnicos que se desean eliminar.
     * @return `true` si los datos técnicos se eliminan con éxito, de lo contrario, `false`.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDataTecnico(@PathVariable Long id) {
        boolean deleted = dataTecnicoService.deleteDataTecnico(id);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza datos técnicos por su ID con los detalles proporcionados.
     *
     * @param id El ID de los datos técnicos que se desean actualizar.
     * @param dataTecnico Los nuevos detalles de los datos técnicos.
     * @return `true` si los datos técnicos se actualizan con éxito, de lo contrario, `false`.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateDtaTecnico(@PathVariable Long id, @RequestBody DataTecnico dataTecnico) {
        DataTecnico updateDataTecnico = dataTecnicoService.updateDataTecnico(id, dataTecnico);
        if (updateDataTecnico != null) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea muchos datos tecnicos a travez de un  archivo de datos técnicos proporcionado.
     *
     * @param file El archivo de datos técnicos a ser leidos y posteriormente guardados.
     * @return El resultado de la operación y un estado OK, o una respuesta mala solicitud si "no se proporciona un archivo".
     */
    @PostMapping("/savefile")
    public ResponseEntity<String> saveFileDataTecnico(@RequestParam("file") MultipartFile file){
        
        if(file != null){
            return dataTecnicoService.saveFile(file);
        }else{
            return ResponseEntity.badRequest().body("No se proporcionó ningún archivo.");
        }
    }
    
}   