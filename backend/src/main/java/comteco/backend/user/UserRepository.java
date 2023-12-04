package comteco.backend.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import comteco.backend.person.Person;

/**
 * Interfaz que define un repositorio para la entidad 'User'.
 * Esta interfaz extiende JpaRepository para proporcionar operaciones de acceso a datos de 'User'.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     /**
     * Busca un usuario por su nombre de usuario, recuperando también la información de su entidad 'Person'.
     *
     * @param username El nombre de usuario del usuario que se busca.
     * @return Un objeto Optional que contiene el usuario si se encuentra, incluyendo la información de la entidad 'Person' asociada.
     */    
    @EntityGraph(attributePaths = "person")
    Optional<User> findByUsername(String username);

    /**
     * Busca un usuario por su entidad 'Person' asociada.
     *
     * @param person La entidad 'Person' asociada al usuario que se busca.
     * @return Un objeto Optional que contiene el usuario si se encuentra.
     */
    Optional<User> findByPerson(Person person);
}
