package comteco.backend.ordenDia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDiaRepository extends JpaRepository<OrdenDia, Long>{
   
}
