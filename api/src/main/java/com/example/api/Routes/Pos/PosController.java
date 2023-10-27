package com.example.api.Routes.Pos;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controlador REST para la gestion de Posiciones
 */
@RestController
@RequestMapping("/api/v1/pos")
@CrossOrigin("*")
@AllArgsConstructor
public class PosController {
    
    private PosService posService;

    /**
     * Obtiene todas las Posiciones disponibles
     * 
     * @return una lista de todas las Posiciones
     */
    @GetMapping
    public ResponseEntity<List<Pos>> getAllPoss(){
        List<Pos> poss = posService.getAllPoss();
        return new ResponseEntity<>(poss, HttpStatus.OK);
    }

    @GetMapping("/bynap/{id_nap}")
    public ResponseEntity<List<Pos>> getAllPossByNap(@PathVariable Long id_nap){
        return posService.getAllPossByNap(id_nap);
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
