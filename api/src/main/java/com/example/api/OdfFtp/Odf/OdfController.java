package com.example.api.OdfFtp.Odf;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/odf")
@CrossOrigin("*")
@AllArgsConstructor
public class OdfController{

    private OdfService OdfService;

    @GetMapping
    public ResponseEntity<List<Odf>> getAllOdf() {
        List<Odf> Odfs = OdfService.getAllOdfs();
        return new ResponseEntity<>(Odfs, HttpStatus.OK);
    }
}