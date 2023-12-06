package comteco.backend.ordenDia.planComercial;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/plan_comercial")
public class PlanComercialController {
    
    private PlanComercialService planComercialService;
    
    @GetMapping
    public ResponseEntity<List<PlanComercial>> getAllPlanesComerciales() {
        List<PlanComercial> planesComerciales = planComercialService.getAllPlanesComerciales();
        return new ResponseEntity<>(planesComerciales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanComercial> getPlanComercialById(@PathVariable Long id) {
        return planComercialService.getPlanComercialById(id)
                .map(planComercial -> new ResponseEntity<>(planComercial, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PlanComercial> savePlanComercial(@RequestBody PlanComercial planComercial) {
        PlanComercial savedPlanComercial = planComercialService.savePlanComercial(planComercial);
        return new ResponseEntity<>(savedPlanComercial, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanComercial(@PathVariable Long id) {
        planComercialService.deletePlanComercial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
