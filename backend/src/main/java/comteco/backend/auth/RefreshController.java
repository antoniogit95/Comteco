package comteco.backend.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Controlador REST para la gestión de autentificacion.
 * Todas las rutas estan en el contexto auth /auth 
 */
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RefreshController {
    
    private AuthService authService;

    @PostMapping("/refresh_token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody LoginRequest login) {
        System.out.println("Entrado a refrscar el toquen:  "+login.getUsername());
        AuthResponse response = authService.refreshToken(login.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
