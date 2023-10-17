package com.example.api.Person;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person updatePerson(Long id, Person person) {
        if (personRepository.existsById(id)) {
            return personRepository.save(person);
        }
        return null;
    }

    public boolean deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }
}