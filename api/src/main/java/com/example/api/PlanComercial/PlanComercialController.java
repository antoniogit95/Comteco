package com.example.api.PlanComercial;

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
@RequestMapping("/api/v1/plancomercial")
@CrossOrigin("*")
@AllArgsConstructor
public class PlanComercialController {
    private PlanComercialService planComercialService;

    @GetMapping
    public ResponseEntity<List<PlanComercial>> getAllPlanComercial() {
        return planComercialService.getAllPlanComercial();
    }

    @PostMapping("/savefile")
    public ResponseEntity<String> saveFilePlanComercial(@RequestParam("file") MultipartFile file){
        System.out.println("imprimri para saver si entra a file");
        if(file != null){
            return planComercialService.saveFile(file);
        }else{
            return ResponseEntity.badRequest().body("No se proporcionó ningún archivo.");
        }
    }
}
