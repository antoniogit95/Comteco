package comteco.backend.ordenDia.trabajo;

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
@AllArgsConstructor
@RequestMapping("api/v1/trabajo")
public class TrabajoController {
    
    private TrabajoService trabajoService;

    @GetMapping
    public ResponseEntity<List<Trabajo>> getAllTrabajos() {
        List<Trabajo> trabajos = trabajoService.getAllTrabajos();
        return new ResponseEntity<>(trabajos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trabajo> getTrabajoById(@PathVariable Long id) {
        return trabajoService.getTrabajoById(id)
                .map(trabajo -> new ResponseEntity<>(trabajo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Trabajo> saveTrabajo(@RequestBody Trabajo trabajo) {
        Trabajo savedTrabajo = trabajoService.saveTrabajo(trabajo);
        return new ResponseEntity<>(savedTrabajo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrabajo(@PathVariable Long id) {
        trabajoService.deleteTrabajo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
