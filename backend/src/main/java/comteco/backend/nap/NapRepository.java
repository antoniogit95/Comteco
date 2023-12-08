package comteco.backend.nap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NapRepository extends JpaRepository<Nap, Long>{
    
}
