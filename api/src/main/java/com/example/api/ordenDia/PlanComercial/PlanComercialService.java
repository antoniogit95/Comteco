package com.example.api.ordenDia.PlanComercial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlanComercialService {

    private PlanComercialRepository planComercialRepository;
    
    public ResponseEntity<String> saveFile(MultipartFile file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String linea;
            boolean firstLine = true;
            while ((linea = br.readLine()) != null) {
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                String[] partes = linea.split(";"); 
                System.out.println(partes.length+"---------------------"+partes[0]+"---------------");
                if (partes.length == 28) { 
                    PlanComercial pComercial = PlanComercial.builder()
                    .codLab(partes[1])
                    .nuevoNombre(partes[3])
                    .tipoPlan(partes[5])
                    .nuevaVelocidad(partes[6])
                    .planCorto(partes[8])
                    .build();
                    planComercialRepository.save(pComercial);
                }
            }
            return new ResponseEntity<>("DatosGuardatosExitosamente", HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>("ErrorInesperador", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<PlanComercial>> getAllPlanComercial() {
        try {
            List<PlanComercial> planCOptional = planComercialRepository.findAll();
            return new ResponseEntity<>(planCOptional, HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
    }

    public ResponseEntity<PlanComercial> getPlanComercialById(Long id) {
        try {
            Optional<PlanComercial> pcOptional = planComercialRepository.findById(id);
            return new ResponseEntity<>(pcOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<PlanComercial> createPlanComercial(PlanComercial planComercial) {
        try {
            planComercialRepository.save(planComercial);
            return new ResponseEntity<>(planComercial, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<PlanComercial> updatePlanComercial(Long id, PlanComercial planComercial) {
        try {
            Optional<PlanComercial> pcOptional = planComercialRepository.findById(id);
            if (pcOptional.isPresent()) {
                planComercial.setId(id);
                planComercialRepository.save(planComercial);
                return new ResponseEntity<>(planComercial, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(planComercial, HttpStatus.FAILED_DEPENDENCY);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Boolean> deletePlanComercial(Long id) {
        try {
            planComercialRepository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

}