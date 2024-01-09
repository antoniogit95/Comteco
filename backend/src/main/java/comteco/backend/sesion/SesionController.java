package comteco.backend.sesion;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comteco.backend.auth.LoginRequest;
import comteco.backend.person.Person;
import comteco.backend.person.PersonService;
import comteco.backend.user.User;
import comteco.backend.user.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
* Controlador REST para la gestión de cesiones.
* todas las rutas estan el contexto auth api/v1/cesion 
*/
@RestController
@RequestMapping("api/v1/cesion")
@CrossOrigin("*")
@AllArgsConstructor
public class SesionController {
 
    private SesionService cesionService;
    private PersonService personService;
    private UserRepository userRepository;

    /**
    * Debulve todas las cesiones que estan activas en ese momento
    *
    * @return List<Cesiones> una lista de todas las cesiones activas.
    */
    @GetMapping("/actives")
    public ResponseEntity<List<Sesion>> GetAllActives() {
        Optional<List<Sesion>> optionaCesion = cesionService.getCesionsActives();
        if (optionaCesion.isPresent()) {
            return new ResponseEntity<>(optionaCesion.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * 
     * @param loginRequest obgeto donde iniciamos el usuario para posteriormente cerrar la cesion;
     * @return la cesion modificada.
     */
    @PostMapping("/close")
    public ResponseEntity<Sesion> CerrarSesionByUser(@RequestBody LoginRequest loginRequest) {        
        Sesion sesion = cesionService.cerrarSesionByUser(loginRequest.getUsername()); 
        return sesion != null ? new ResponseEntity<>(sesion, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    
    /**
     * 
     * @param loginRequest obgeto donde iniciamos el usuario para posteriormente cerrar la cesion;
     * @return la cesion de la ultima conexion
     */
    @GetMapping("/ultima/{id}")
    public ResponseEntity<Sesion> getUltimaConexion(@PathVariable Long id) {        
        Sesion sesion = cesionService.obtenerUltimaConexion(id); 
        return sesion != null ? new ResponseEntity<>(sesion, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Devuelve una lista de cesion por el id_persona.
     *
     * @param id_person El id_person de la persona asociada a una persona que asu ves esta
     *  asociada a un usuario que asu ves esta asociada a una cesion.
     * @return Una respuesta que muestra un listado de todas las cesiones que tubo un usuario.
     */
    @GetMapping("/allactivesperson/{id}")
    public ResponseEntity<List<Sesion>> GetAllCesionForIdPerson(@PathVariable Long id) {
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
