package comteco.backend.ordenDia.servicio;

import comteco.backend.ordenDia.OrdenDia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @GeneratedValue
    private Long id;

    private Long cod;
    private String claseServicio;
    private String numeroServicio;
    private String componente;
    private String estado;
    private String areaServicio;

    @ManyToOne
    @JoinColumn(name = "id_orden_dia", nullable = false)
    private OrdenDia ordenDia;
}
