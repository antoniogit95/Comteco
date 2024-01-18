package comteco.backend.ordenDia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * clase para usar como respuesta de una consulta dada.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDiaResponseByProducto {
    
    private String nap;
    private String posicion;
    private String datoTecnico;
    private String zona;
    private String ubicacion;
    private String direccion;
    private boolean status;
}
