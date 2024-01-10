package comteco.backend.person;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa a una persona en el sistema
**/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    /**
     * Identificador único del usuario.
     * Se genera de manera automatica
    */
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true) //permite que solo se registre un carnet de identidad.
    private String cedulaIdentidad;
    
    private String nombre;
    private String apellidos;


    @Column(unique = true) //permite que solo se registre un item o numero de interno
    private String item;
    
    private Date fecha_nacimiento;
    
    @Column(unique = true) //permite que solo se registre un email que no se dupliquen
    private String email;
    
    private String telefono;
    private boolean status;
    private Timestamp createdAt;
    private Timestamp updateAt;

}
