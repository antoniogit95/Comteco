package comteco.backend.ordenDia.vendedor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Creacion de la tabla Vendedor
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vendedor {
    @Id
    @GeneratedValue
    private long id;
    private String puntoVenta;
    private String vendedor;
}
