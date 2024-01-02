package comteco.backend.dataTecnico;

import java.sql.Date;
import java.util.Calendar;

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

    public Date getFechaFinalAdd1Day(){
        Date aux = fechaFinal;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(aux);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return new Date(calendar.getTimeInMillis());
    }
}