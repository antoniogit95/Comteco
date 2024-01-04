package comteco.backend.nap.posicion;

import comteco.backend.nap.Nap;
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
 * Entidad Posicion a ser registrado en la base de datos
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posicion {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String cod; // De 4 dígitos, rellenar con 0000
    private boolean estado;
    /**
     * Relacion con la Tabla Nap por medio de su id_nap
     */
    @ManyToOne
    @JoinColumn(name = "id_nap")
    private Nap nap;

}
