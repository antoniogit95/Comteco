package comteco.backend.ordenDia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDiaRepository extends JpaRepository<OrdenDia, Long>{

    /**
     * 
     * @param producto recibe un producto de tipo long
     * @return una lista de todas las ordenes del dia relacionadas a ese producto
     */
    List<OrdenDia> findAllByProducto(Long producto);
   
}
