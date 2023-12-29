package comteco.backend.dataTecnico;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase para usar como metodo de entrada para buscar porductos por producto o por un intervalo de fechas;
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataTecnicoRequesBusqueda {

    private Date fechaInicio;
    private Date fechaFinal;

}