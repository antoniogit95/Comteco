package comteco.backend.ordenDia.trabajo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trabajo {
    
    @Id
    private Long id;

    private Long codTipo;
    private String tipoTrabajo;
    private String resumido;
}
