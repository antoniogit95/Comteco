package comteco.backend.dataTecnico;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatoTecnicoRequest {
    private String nuevoNap;
    private String antogupNap;
    private Long producto;
    private String username;
    private String observaciones;
}
