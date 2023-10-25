package com.example.api.Routes.Fdt;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/fdt")
@CrossOrigin("*")
@AllArgsConstructor
public class FdtController{

    private FdtService fdtService;

    @GetMapping
    public ResponseEntity<List<Fdt>> getAllFtp() {
        List<Fdt> ftps = fdtService.getAllFdts();
        return new ResponseEntity<>(ftps, HttpStatus.OK);
    }

    @GetMapping("/byodf/{id_odf}")
    public ResponseEntity<List<Fdt>> getAllFdtByOdf(@PathVariable Long id_odf){
        System.out.println("aciendo pruebas: "+id_odf);
        return fdtService.getAllFdtsByOdf(id_odf);
    }
}