package comteco.backend.nap.posicion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comteco.backend.nap.Nap;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de la Clase posicion que hace conexion con la base de datos por JPA
 */
@Repository
public interface PosicionRepository extends JpaRepository<Posicion, Long>{
    
    /**
     * 
     * @param cod a preguntar si existe en la caja nap.
     * @param nap caja nap de donde se preguntra si existe alguna posicion.
     * @return en caso que existan ambos componentes retornara true caso contrario false.
     */
    boolean existsByCodAndNap(String cod, Nap nap);

    /**
     * 
     * @param cod codigo por el cual va buscar una posicion.
     * @param nap caja nap de donse sacara la posicion.
     * @return retornara un Obgeto de tipo Optional con contiene una posicion o null.
     */
    Optional<Posicion> findByCodAndNap(String cod, Nap nap);

    /**
     * @param nap nap a ser buscada en la base de datos
     * @return una lista de todas las posiciones relacionadas a la nap.
     */
    List<Posicion> findAllByNap(Nap nap);
}
