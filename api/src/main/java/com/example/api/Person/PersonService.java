package com.example.api.Person;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Servicio que gestiona los servicios de la clase person.
 */
@AllArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

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
}