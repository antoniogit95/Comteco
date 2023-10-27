package com.example.api.archivo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/files")
@AllArgsConstructor
@CrossOrigin("*")
public class FileController {
    
    private FileService archiveService;

    @GetMapping("/get")
    public List<File> obtenerArchivos(){
        return archiveService.getFilesAll();
        
    }

    @PostMapping("/save")
    public ResponseEntity<String> cargarArchivo(@RequestParam("archive") MultipartFile archivo){
        return archiveService.saveFile(archivo);
    }
}