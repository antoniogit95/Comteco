package comteco.backend.ordenDia.ubicacionTecnica;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/v1/ubicacion_tecnica")
@AllArgsConstructor
public class UbicacionTecnicaController {
    
    private UbicacionTecnicaService ubicacionTecnicaService;
    
    @GetMapping
    public ResponseEntity<List<UbicacionTecnica>> getAllUbicacionesTecnicas() {
        List<UbicacionTecnica> ubicacionesTecnicas = ubicacionTecnicaService.getAllUbicacionesTecnicas();
        return new ResponseEntity<>(ubicacionesTecnicas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionTecnica> getUbicacionTecnicaById(@PathVariable Long id) {
        return ubicacionTecnicaService.getUbicacionTecnicaById(id)
                .map(ubicacionTecnica -> new ResponseEntity<>(ubicacionTecnica, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UbicacionTecnica> saveUbicacionTecnica(@RequestBody UbicacionTecnica ubicacionTecnica) {
        UbicacionTecnica savedUbicacionTecnica = ubicacionTecnicaService.saveUbicacionTecnica(ubicacionTecnica);
        return new ResponseEntity<>(savedUbicacionTecnica, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacionTecnica(@PathVariable Long id) {
        ubicacionTecnicaService.deleteUbicacionTecnica(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
