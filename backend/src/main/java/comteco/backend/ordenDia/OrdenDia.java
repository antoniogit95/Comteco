package comteco.backend.ordenDia;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
public class OrdenDia {
    
    @Id
    @GeneratedValue()
    private Long id;

    private Timestamp fecha;
    private Long idSolicitud;
    private Long idTrabajo;
    private Long idServicio;
    private Long idUbicacionTecnica;
    private Long producto;
    private Long idEquipos;
    private boolean estado;
    private Long idPos;
    private Long idCliente;
    private String estadoOt;
    private Long idTecnico;
}
