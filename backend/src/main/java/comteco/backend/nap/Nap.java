package comteco.backend.nap;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad Nap a ser creada en la base de datos
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Nap {
    
    @Id 
    @GeneratedValue
    private Long id;

    private String odf;
    private String fdt;
    private String nap;

    // para que en la columna cod, que significa el codigo de la ruta nap sea unico
    @Column(unique = true)
    private String cod;
    
    private String ubicacion;
    private boolean estado;
    private String descripcion;
}
