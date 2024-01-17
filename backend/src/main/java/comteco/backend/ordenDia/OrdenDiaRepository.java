package comteco.backend.ordenDia;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comteco.backend.nap.posicion.Posicion;

/*
 * Conexion con la Base de Datos en la entidad orden_dia 
 */
@Repository
public interface OrdenDiaRepository extends JpaRepository<OrdenDia, Long>{

    /**
     * 
     * @param producto recibe un producto de tipo long
     * @return una lista de todas las ordenes del dia relacionadas a ese producto
     */
    List<OrdenDia> findAllByProducto(Long producto);

    /**
     * 
     * @param producto recibe un producto de tipo Long a buscar en la base de datos
     * @return un Objeto si tiene una Orden Dia o no
     */
    Optional<OrdenDia> findByProducto(Long producto);

    /**
     * 
     * @param nuevaPosicion a buscar si en esa pocicion ya hay un producto asignado
     * @return debulve una orden del dia en caso de tener con esa posicion,
     */
    Optional<OrdenDia> findByPosicion(Posicion nuevaPosicion);

    /**
     * 
     * @param fechaInicio a iniciar la busqueda de las ordenes del dia
     * @param fechaFinal a finalizar la busqueda de las ordenes del dia
     * @return todas las ordenenes del dia en las fechas comprendidas.
     */
    List<OrdenDia> findAllByFechaBetween(Timestamp fechaInicio, Timestamp fechaFinal);
   
}
