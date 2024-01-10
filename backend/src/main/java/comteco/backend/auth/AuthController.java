package comteco.backend.auth;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para la gestión de autentificacion.
 * Todas las rutas estan en el contexto auth /auth 
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping
    public ResponseEntity<String> pruebaGet(){
        return new ResponseEntity<>("mensaje desde back end", HttpStatus.OK);
    }
    
    /**
     * Inicia sesión de un usuario.
     *
     * @param request La solicitud de inicio de sesión que incluye las credenciales del 
     * usuario dentro la clase LoginReques.
     * @return Una respuesta que contiene la información de autenticación del usuario 
     * si el inicio de sesión es exitoso que estan dentro la clase AuthResponse.
     */

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

     /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request La solicitud de registro que incluye los datos del nuevo usuario 
     * dentro la clase RegisterRequest.
     * @return Una respuesta que contiene la información de autenticación del usuario 
     * si el registro es exitoso con los datos que estan dentro la clase AuthResponse.
     */
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping(value = "register")
    public ResponseEntity<String> registerGet(){
        return new ResponseEntity<>("registro exitoso", HttpStatus.OK);
    }


    /**
     * 
     * @param request donde esta el usarme a buscar si existe o no
     * @return un objeto de tipo boleano si existe false, 
     */
    @PostMapping("/checkusername")
    public ResponseEntity<Boolean> checkUsernameAviable(@RequestBody LoginRequest request){
        System.out.println(request.getUsername());
        Boolean existeUsername = authService.existUsernme(request.getUsername());
        return existeUsername ? new ResponseEntity<>(true, HttpStatus.OK) : 
            new ResponseEntity<>(false, HttpStatus.OK);
    }

    /**
     * 
     * @param request donde esta el ci a buscar si existe o no
     * @return un objeto de tipo boleano si existe false, 
     */
    @PostMapping("/checkci")
    public ResponseEntity<Boolean> checkCiAviable(@RequestBody LoginRequest request){
        System.out.println(request.getUsername());
        Boolean existeCi = authService.existCi(request.getUsername());
        return existeCi ? new ResponseEntity<>(true, HttpStatus.OK) : 
            new ResponseEntity<>(false, HttpStatus.OK);
    }

    /**
     * 
     * @param request donde esta el Item a buscar si existe o no
     * @return un objeto de tipo boleano si existe false, 
     */
    @PostMapping("/checkitem")
    public ResponseEntity<Boolean> checkItemAviable(@RequestBody LoginRequest request){
        System.out.println(request.getUsername());
        Boolean existeItem = authService.existItem(request.getUsername());
        return existeItem ? new ResponseEntity<>(true, HttpStatus.OK) : 
            new ResponseEntity<>(false, HttpStatus.OK);
    }

    /**
     * 
     * @param request donde esta los datos de ci, item y el correo que desea restablecer contraseña
     * @return un objeto de tipo boleano si existe o no. 
     */
    @PostMapping("/checkData")
    public ResponseEntity<ForgenPasswordRequest> checkData(@RequestBody ForgenPasswordRequest request){
        ForgenPasswordRequest response = authService.existDataByForgenPassword(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 
     * @param request donde esta los datos de ci, item , el correo y la contraseña que se dese cambiar
     * @return un objeto de tipo boleano si existe o no. 
     */
    @PostMapping("/forgen_password")
    public ResponseEntity<ForgenPasswordRequest> saveNewPasswordByEmail(@RequestBody ForgenPasswordRequest request){
        ForgenPasswordRequest response = authService.existDataByForgenPassword(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
