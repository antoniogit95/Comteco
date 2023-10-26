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

@RestController
@RequestMapping("/api/v1/datatecnico")
@CrossOrigin("*")
@AllArgsConstructor
public class DataTecnicoController {
    
    private DataTecnicoService dataTecnicoService;

    @GetMapping("/{id}")
    public ResponseEntity<DataTecnico> getDataTecnicoById(@PathVariable Long id){
        DataTecnico dataTecnico = dataTecnicoService.getDataTecnicoById(id);
        if(dataTecnico != null){
            return new ResponseEntity<>(dataTecnico, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DataTecnico> createDataTecnico(@RequestBody DataTecnicoRequest request){
        return ResponseEntity.ok(dataTecnicoService.createRegisterDataTecnico(request));
    }

    @GetMapping
    public ResponseEntity<List<DataTecnico>> getAllDataTecnicos() {
        try {
            List<DataTecnico> dataTecnicos = dataTecnicoService.getAllDataTecnicos();
            return new ResponseEntity<>(dataTecnicos, HttpStatus.OK);  
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDataTecnico(@PathVariable Long id) {
        boolean deleted = dataTecnicoService.deleteDataTecnico(id);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateDtaTecnico(@PathVariable Long id, @RequestBody DataTecnico dataTecnico) {
        DataTecnico updateDataTecnico = dataTecnicoService.updateDataTecnico(id, dataTecnico);
        if (updateDataTecnico != null) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/savefile")
    public ResponseEntity<String> saveFileDataTecnico(@RequestParam("file") MultipartFile file){
        
        System.out.println("agarrando el archivo file ////////-------------------------------------------");
        
        if(file != null){
            return dataTecnicoService.saveFile(file);
        }else{
            return ResponseEntity.badRequest().body("No se proporcionó ningún archivo.");
        }
    }
    
}   