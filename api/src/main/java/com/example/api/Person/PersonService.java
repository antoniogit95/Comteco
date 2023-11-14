package com.example.api.Person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.User.User;
import com.example.api.User.UserRepository;

import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Servicio que gestiona los servicios de la clase person.
 */
@AllArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private UserRepository userRepository;

    /**
     * Obtiene una entidad 'Person' por su ID.
     *
     * @param id El ID de la persona que se busca.
     * @return La entidad 'Person' si se encuentra, de lo contrario, devuelve null.
     */
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todas las entidades 'Person' disponibles.
     *
     * @return Una lista de todas las entidades 'Person'
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Actualiza una entidad 'Person' por su ID con los datos proporcionados.
     *
     * @param id     El ID de la persona que se desea actualizar.
     * @param person Los nuevos datos de la persona.
     * @return La entidad 'Person' actualizada si se encuentra, de lo contrario, devuelve null.
     */
    public Person updatePerson(Long id, Person person) {
        if (personRepository.existsById(id)) {
            return personRepository.save(person);
        }
        return null;
    }

    /**
     * Elimina una entidad 'Person' por su ID.
     *
     * @param id El ID de la persona que se desea eliminar.
     * @return true si la entidad se elimina con éxito, de lo contrario, devuelve un false.
     */
    public boolean deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ResponseEntity<String> ActivePersonAndUser(Long id) {
        try {
            Optional<Person> personOptional = personRepository.findById(id);
            Person person = personOptional.get();
            person.setStatus(true);
            person.setUpdate_at(getTimestamp());
            personRepository.save(person);
            Optional<User> userOptional = userRepository.findByPerson(person);
            User user = userOptional.get();
            user.setStatus(true);
            user.setUpdate_at(getTimestamp());
            userRepository.save(user);
            return new ResponseEntity<>("Usuario Validado Exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al momento de validar", HttpStatus.NOT_FOUND);

        }
    }

    private Timestamp getTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now);
    }
}