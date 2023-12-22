package comteco.backend.dataTecnico;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Response para Dato Tecnico
 *  aumentadno el atributo messague donde entrara cualquier error que se haga al momento de guardar un dato tecnico
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataTecnicoResponse {
    
    private Long id;
    private String username;
    private Long producto;
    private String nuevaPosicion;
    private String antiguaPosicion;
    private String obeservaciones;
    private Timestamp createdAt;
    private Timestamp updateAt;   
    private String message; //poner algun error
}
