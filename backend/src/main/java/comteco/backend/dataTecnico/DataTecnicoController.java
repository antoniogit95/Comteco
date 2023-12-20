package comteco.backend.dataTecnico;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/data_tecnico")
public class DataTecnicoController {
    
    private DataTecnicoService dataTecnicoService;

    @GetMapping("/{id}")
    public ResponseEntity<DataTecnico> getDataTecnicoById(@PathVariable Long id) {
        Optional<DataTecnico> dOptional = dataTecnicoService.getDataTecnicoById(id);
        if(dOptional.isPresent()){
            return new ResponseEntity<>(dOptional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<DataTecnico> saveDataTecnico(@RequestBody DataTecnico dataTecnico) {
        DataTecnico savedDataTecnico = dataTecnicoService.saveDataTecnico(dataTecnico);
        return new ResponseEntity<>(savedDataTecnico, HttpStatus.CREATED);
    }
    
    
}
