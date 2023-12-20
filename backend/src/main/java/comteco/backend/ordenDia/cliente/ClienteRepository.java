package comteco.backend.ordenDia.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Conexion con la base de datos 
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
