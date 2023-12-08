package comteco.backend.ordenDia.planComercial;

import comteco.backend.ordenDia.equipo.Equipo;
//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @GeneratedValue
    private Long id;

    private String planComercial;
    private String codLab;
    
    private String nuevoNombre;
    private String tipoPlan;
    private String nuevaVelocidad;
    private String planCorto;
    private String tipoEquipo;

    @OneToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;
}
