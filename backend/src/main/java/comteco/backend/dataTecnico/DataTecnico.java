package comteco.backend.dataTecnico;

import java.sql.Timestamp;

import comteco.backend.nap.posicion.Posicion;
import comteco.backend.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entidad de la tabla Data Tecnico
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DataTecnico {
    @Id
    @GeneratedValue
    private Long id;
    
    /**
     * Relacion con la tabla User donde un usuario puede crear muchos datos tecnicos
     */
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private Long producto;

    /**
     * Relacion con la tabla posicion que tiene todos los datos de la caja nap 
     */
    @ManyToOne
    @JoinColumn(name = "id_nueva_posicion")
    private Posicion nuevaPosicion;

    /**
     * Relacion con la tabla posicion que tiene todos los datos de la caja nap 
     */
    @ManyToOne
    @JoinColumn(name = "id_antigua_posicion")
    private Posicion antiguaPosicion;

    private String obeservaciones;
    private boolean status;
    private Timestamp createdAt;
    private Timestamp updateAt;
}