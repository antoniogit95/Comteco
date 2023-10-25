package com.example.api.Routes.Odf;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/odf")
@CrossOrigin("*")
@AllArgsConstructor
public class OdfController{

    private OdfService odfService;

    @GetMapping
    public ResponseEntity<List<Odf>> getAllOdf() {
        List<Odf> Odfs = odfService.getAllOdfs();
        return new ResponseEntity<>(Odfs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odf> getOdfById(@PathVariable Long id) {
        Optional<Odf> optionalOdf = odfService.getOdfById(id);
        if(optionalOdf.isPresent()){
            return new ResponseEntity<>(optionalOdf.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}