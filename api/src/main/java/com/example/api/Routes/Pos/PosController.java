package com.example.api.Routes.Pos;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/pos")
@CrossOrigin("*")
@AllArgsConstructor
public class PosController {
    
    private PosService posService;

    @GetMapping
    public ResponseEntity<List<Pos>> getAllPoss(){
        List<Pos> poss = posService.getAllPoss();
        return new ResponseEntity<>(poss, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addNewRoute(@RequestBody String srt) {
        return posService.addNewPos(srt);
    }

    @PostMapping("/allroutes")
    public List<ResponseEntity<String>> addAllNewRoute(@RequestBody String srt) {
        return posService.addAllNewPos(srt);
    }
}
