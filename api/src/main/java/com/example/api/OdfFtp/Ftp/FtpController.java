package com.example.api.OdfFtp.Ftp;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/ftp")
@CrossOrigin("*")
@AllArgsConstructor
public class FtpController{

    private FtpService ftpService;

    @GetMapping
    public ResponseEntity<List<Ftp>> getAllFtp() {
        List<Ftp> ftps = ftpService.getAllFtps();
        return new ResponseEntity<>(ftps, HttpStatus.OK);
    }

}