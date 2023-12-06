package comteco.backend.ordenDia.planComercial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanComercialRepository extends JpaRepository<PlanComercial, Long>{
    
}
