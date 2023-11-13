package com.example.api.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.Person.Person;
import com.example.api.Person.PersonService;

import lombok.AllArgsConstructor;

/**
 * Controlador REST para la gestión de usuarios.
 * todas las rutas estan el contexto auth api/v1/user 
 */
@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    
    UserRepository userRepository;
    PersonService personService;

    /**
     * Activa un usuario por id_person de la persona.
     *
     * @param id_person El id_person de la persona asociada a una persona que asu ves esta asociada a un usuario
     * @return Una respuesta que indica si el usuario se activó exitosamente.
     */
    @PutMapping("/active/{id_person}")
    public ResponseEntity<String> activarUsuarioPorIdPersona(@PathVariable Long id_person) {
        Person person = personService.getPersonById(id_person);
        Optional<User> userOptional = userRepository.findByPerson(person);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus(true);
            user.setUpdate_at(getTimestamp());
            userRepository.save(user);
            return ResponseEntity.ok("Usuario activado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/desactive/{id_person}")
    public ResponseEntity<String> desactiveUsuarioPorIdPersona(@PathVariable Long id_person) {
        Person person = personService.getPersonById(id_person);
        Optional<User> userOptional = userRepository.findByPerson(person);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus(false);
            user.setUpdate_at(getTimestamp());
            userRepository.save(user);
            return ResponseEntity.ok("Usuario Desactivado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    /**
     * Verifica si un usuario está activo por id_person de la persona.
     *
     * @param id_person El ID de la persona asociada al usuario cuyo estado se verifica.
     * @return Una respuesta que indica si el usuario está activo o no.
     */
    @GetMapping("/isValidate/{id_person}")
    public ResponseEntity<Boolean> isValidateUser(@PathVariable Long id_person) {
        Person person = personService.getPersonById(id_person);
        Optional<User> userOptional = userRepository.findByPerson(person);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if(user.isStatus()){
                return ResponseEntity.ok(true);
            }else{
                return ResponseEntity.ok(false);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Timestamp getTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now);
    }
}
