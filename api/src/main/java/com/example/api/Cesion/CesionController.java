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

/**
* Controlador REST para la gestión de cesiones.
* todas las rutas estan el contexto auth api/v1/cesion 
*/
@RestController
@RequestMapping("api/v1/cesion")
@CrossOrigin("*")
@AllArgsConstructor
public class CesionController {
 
    private CesionService cesionService;
    private PersonService personService;
    private UserRepository userRepository;

    /**
    * Debulve todas las cesiones que estan activas en ese momento
    *
    * @return List<Cesiones> una lista de todas las cesiones activas.
    */
    @GetMapping("/actives")
    public ResponseEntity<List<Cesion>> GetAllActives() {
        Optional<List<Cesion>> optionaCesion = cesionService.getCesionsActives();
        if (optionaCesion.isPresent()) {
            return new ResponseEntity<>(optionaCesion.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    

    /**
     * Devuelve una lista de cesion por el id_persona.
     *
     * @param id_person El id_person de la persona asociada a una persona que asu ves esta
     *  asociada a un usuario que asu ves esta asociada a una cesion.
     * @return Una respuesta que muestra un listado de todas las cesiones que tubo un usuario.
     */
    @GetMapping("/allactivesperson/{id}")
    public ResponseEntity<List<Cesion>> GetAllCesionForIdPerson(@PathVariable Long id) {
        System.out.println("prueba de imprecion" + id);
        return cesionService.getAllCesionsByUser(id);
    }

    /**
    * Devuelve una cesion por el id_persona.
    *
    * @param id_person El id_person de la persona asociada a una persona que asu ves esta
    *  asociada a un usuario que asu ves esta asociada a una cesion.
    * @return Una respuesta que indica si la cesion esta activa o no.
    */
    @GetMapping("/actives/{id}")
    public ResponseEntity<Boolean> GetActiveForIdPerson(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if(person != null){
            Optional<User> userOptional = userRepository.findByPerson(person);
            if(userOptional.isPresent()){
                return cesionService.getCesionsActivesByUser(userOptional.get());
            }
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
    }
}
