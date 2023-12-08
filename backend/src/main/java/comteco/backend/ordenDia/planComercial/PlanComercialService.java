package comteco.backend.ordenDia.planComercial;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlanComercialService {

    private PlanComercialRepository planComercialRepository;

    public List<PlanComercial> getAllPlanesComerciales() {
        return planComercialRepository.findAll();
    }

    public Optional<PlanComercial> getPlanComercialById(Long id) {
        return planComercialRepository.findById(id);
    }

    public PlanComercial savePlanComercial(PlanComercial planComercial) {
        return planComercialRepository.save(planComercial);
    }

    public void deletePlanComercial(Long id) {
        planComercialRepository.deleteById(id);
    }
}
