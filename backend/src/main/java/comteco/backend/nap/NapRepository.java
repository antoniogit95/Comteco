package comteco.backend.nap;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NapRepository extends JpaRepository<Nap, Long>{

    /**
     * 
     * @param cod codio de la caja nap a pregutar si existe en la base de datos
     * @return devlver true si existe caso contrario retornar false
     */
    boolean existsByCod(String cod);

    /**
     * 
     * @param cod buscar una caja nap por su codigo
     * @return un objeto de tipo Optional que contenga una nap
     */
    Optional<Nap> findByCod(String cod);

    /**
     * @return devuelve una lista de todos los cod --> codigos de la tabla naps
     */
    List<String> findAllCod();
    
}
