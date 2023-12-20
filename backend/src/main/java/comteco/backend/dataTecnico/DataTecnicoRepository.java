package comteco.backend.dataTecnico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Conexion con la base de Datos en la Tabla data_tecnico
 */
@Repository
public interface DataTecnicoRepository extends JpaRepository<DataTecnico, Long>{
    
}
