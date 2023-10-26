package com.example.api.Person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/**
 * Controlador REST para la gestión de entidades 'Person'.
 * En la siguiente ruta /api/v1/person
 */
@RestController
@RequestMapping("/api/v1/person")
@CrossOrigin("*")
@AllArgsConstructor
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * Obtiene una entidad 'Person' por su ID.
     *
     * @param id El ID de la persona que se busca.
     * @return La entidad 'Person' si se encuentra, de lo contrario, devuelve NOT FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene todas las entidades 'Person' disponibles.
     *
     * @return Una lista de todas las entidades 'Person' y un estado OK.
     */
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    /**
     * Elimina una entidad 'Person' por su ID.
     *
     * @param id El ID de la persona que se desea eliminar.
     * @return NO CONTENT si la entidad se elimina con éxito, de lo contrario, devuelve NOT FOUND.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        boolean deleted = personService.deletePerson(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

     /**
     * Actualiza una entidad 'Person' por su ID con los datos proporcionados.
     *
     * @param id     El ID de la persona que se desea actualizar.
     * @param person Los nuevos datos de la persona.
     * @return La entidad 'Person' actualizada si se encuentra y un estado OK, de lo contrario, devuelve NOT FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person updatedPerson = personService.updatePerson(id, person);
        if (updatedPerson != null) {
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
