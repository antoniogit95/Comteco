package comteco.backend.dataTecnico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Conexion con la base de Datos en la Tabla data_tecnico
 */
@Repository
public interface DataTecnicoRepository extends JpaRepository<DataTecnico, Long>{

    /**
     * 
     * @param producto producto a ser buscado en la base de datos
     * @return todos los datos tecnicos que esten relacionados con el producto
     */
    List<DataTecnico> findAllByProduct(Long producto);
    
}
