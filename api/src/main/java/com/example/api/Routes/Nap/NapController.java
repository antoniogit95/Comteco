package com.example.api.Routes.Nap;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/Nap")
@CrossOrigin("*")
@AllArgsConstructor
public class NapController {
    
    private NapService napService;

    @GetMapping()
    public ResponseEntity<List<Nap>> getAllNaps() {
        List<Nap> naps = napService.getAllNaps();
        return new ResponseEntity<>(naps, HttpStatus.OK);
    }
    
}
