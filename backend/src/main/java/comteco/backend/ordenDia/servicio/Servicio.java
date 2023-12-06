package comteco.backend.ordenDia.servicio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Servicio {
    @Id
    private Long id;

    private Long cod;
    private String claseServicio;
    private String numeroServicio;
    private String componente;
    private String estado;
}
