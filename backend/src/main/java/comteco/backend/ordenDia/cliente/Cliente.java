package comteco.backend.ordenDia.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Creacion de la tabla cliente
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue
    private long id;
    private String cliente;
    private String direccion;
    private String tipoCliente;
}
