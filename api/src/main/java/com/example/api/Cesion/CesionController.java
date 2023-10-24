package com.example.api.Cesion;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.Person.Person;
import com.example.api.Person.PersonService;
import com.example.api.User.User;
import com.example.api.User.UserRepository;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("api/v1/cesion")
@CrossOrigin("*")
@AllArgsConstructor
public class CesionController {
 
    private CesionService cesionService;
    private PersonService personService;
    private UserRepository userRepository;

    @GetMapping("/actives")
    public ResponseEntity<List<Cesion>> GetAllActives() {
        Optional<List<Cesion>> optionaCesion = cesionService.getCesionsActives();
        if (optionaCesion.isPresent()) {
            return new ResponseEntity<>(optionaCesion.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/actives/{id}")//este metodo sirve para saber si un usuario esta activo por medio del id de la persona
    public ResponseEntity<Boolean> GetActiveForIdPerson(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        Optional<User> userOptional = userRepository.findByPerson(person);
        if(userOptional.isPresent()){
            return cesionService.getCesionsActivesByUser(userOptional.get().getId());
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
    }
}
