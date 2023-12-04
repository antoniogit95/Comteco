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

    /**
     * Inicia sesión de un usuario.
     *
     * @param request La solicitud de inicio de sesión que incluye las credenciales del 
     * usuario dentro la clase LoginReques.
     * @return Una respuesta que contiene la información de autenticación del usuario 
     * si el inicio de sesión es exitoso que estan dentro la clase AuthResponse.
     */
    
    @GetMapping
    public ResponseEntity<String> pruebaGet(){
        return new ResponseEntity<>("mensaje desde back end", HttpStatus.OK);
    }

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
}
