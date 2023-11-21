package com.example.api.ordenDia.planComercial;

import java.util.List;

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
@RequestMapping("/api/v1/ordendia/plancomercial")
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

    @GetMapping("/{id}")
    public ResponseEntity<PlanComercial> getPlanComercialById(@PathVariable Long id) {
        return planComercialService.getPlanComercialById(id);
    }

    @PostMapping
    public ResponseEntity<PlanComercial> createPlanComercial(@RequestBody PlanComercial planComercial) {
        return planComercialService.createPlanComercial(planComercial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanComercial> updatePlanComercial(@PathVariable Long id, @RequestBody PlanComercial planComercial) {
        return planComercialService.updatePlanComercial(id, planComercial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePlanComercial(@PathVariable Long id) {
        return planComercialService.deletePlanComercial(id);
    }

}
