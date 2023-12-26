package comteco.backend.auth;

import java.sql.Date;

import comteco.backend.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contiene todos los datos necesarios para hacer un nuevo registro del personal.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String celula_identidad;
    private String nombre;
    private String apellidos;
    private String item;
    private Date fecha_nacimiento;
    private String email;
    private String telefono;
    private Role role;
}
