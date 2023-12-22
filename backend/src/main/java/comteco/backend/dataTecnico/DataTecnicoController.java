package comteco.backend.dataTecnico;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Controlador del dato tecnico
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/data_tecnico")
public class DataTecnicoController {
    
    private DataTecnicoService dataTecnicoService;

    @GetMapping
    public ResponseEntity<List<DatoTecnicoReportResponse>> getAllDataTecnico(){
        List<DatoTecnicoReportResponse> responses = dataTecnicoService.getAllDatoTecnico();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * @param Id del dato tecnico a ser obtenido
     * @return devuelve un dato tecnico en caso de existir de lo contrario retorna null;
     */
    @GetMapping("/{id}")
    public ResponseEntity<DataTecnico> getDataTecnicoById(@PathVariable Long id) {
        Optional<DataTecnico> dOptional = dataTecnicoService.getDataTecnicoById(id);
        if(dOptional.isPresent()){
            return new ResponseEntity<>(dOptional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 
     * @param dataTecnicoRequest objeto donde se manda los atributos del dato tecnico a ser requeridos
     * @return un Dato Tecnico response que se manejan los errores dentro ese objeto
     */
    @PostMapping()
    public ResponseEntity<DataTecnicoResponse> saveDataTecnico(@RequestBody DatoTecnicoRequest dataTecnicoRequest) {
        return dataTecnicoService.saveDataTecnico(dataTecnicoRequest);
    }
    
    
}
