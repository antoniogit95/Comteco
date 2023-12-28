package comteco.backend.ordenDia.servicio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicioResponse {
    
    private Long id;
    private Long cod;
    private String claseServicio;
    private String numeroServicio;
    private String componente;
    private String estado;
    private String areaServicio;
    
}
