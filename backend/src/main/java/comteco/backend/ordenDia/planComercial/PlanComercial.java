package comteco.backend.ordenDia.planComercial;

import comteco.backend.ordenDia.equipo.Equipo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlanComercial {
    @Id
    private Long id;

    private String codLab;
    private String nuevoNombre;
    private String tipoPlan;
    private String nuevaVelocidad;
    private String planCorto;

    @OneToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;
}