package comteco.backend.ordenDia.vendedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Conexion la base de datos, con la tabla de Vendedor
 */
@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    
}
