package comteco.backend.ordenDia;


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
    private String estadoOrden;
    private String planComercial;
    private String tipoTramite;
    private String tipoTrabajo;
    private String tipoCliente;
}
