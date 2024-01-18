package comteco.backend.dataTecnico;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DatoTecnicoReportResponse {
    
    private Long id;
    private String nombreCompleto;
    private Long producto;
    private String antiguaPosicion;
    private String nuevaPosicion;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private boolean status;
}
