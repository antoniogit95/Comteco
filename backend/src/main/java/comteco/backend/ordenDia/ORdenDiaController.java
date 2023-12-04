package comteco.backend.ordenDia;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ordendia")
public class ORdenDiaController {
    private  OrdenDiaService ordenDiaService;

    @GetMapping
    public ResponseEntity<List<OrdenDiaResponse>> getAllOrdenDias() {
        return ordenDiaService.getAllOrdenDias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDiaResponse> getOrdenDiaById(@PathVariable Long id) {
        return ordenDiaService.getOrdenDiaById(id);
    }

    @PostMapping
    public ResponseEntity<OrdenDia> createOrdenDia(@RequestBody OrdenDia ordenDia) {
        return ordenDiaService.createOrdenDia(ordenDia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenDia> updateOrdenDia(@PathVariable Long id, @RequestBody OrdenDia ordenDia) {
        return ordenDiaService.updateOrdenDia(id, ordenDia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOrdenDia(@PathVariable Long id) {
        return ordenDiaService.deleteOrdenDia(id);
        
    }
}
