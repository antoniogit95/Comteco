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

/**
 * Entidad Servicio de que poseen las ordenes del dia
 */
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

    /**
     * Relacion de Muchos a 1 donde muhos serviciso esten relacionados a una orden dia
     */
    @ManyToOne
    @JoinColumn(name = "id_orden_dia", nullable = false)
    private OrdenDia ordenDia;
}
