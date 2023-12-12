package comteco.backend.ordenDia;


import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDiaResponse{
    
    private String producto;
    private Timestamp fecha;
    private String estadoOrden;
    private String planComercial;
    private String tipoTramite;
    private String tipoTrabajo;
    private String tipoCliente;
}
