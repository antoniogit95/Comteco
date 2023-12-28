package comteco.backend.ordenDia.servicio;

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
@RequestMapping("/api/v1/servicio")
@AllArgsConstructor
public class ServicioController {
    
    private ServicioService servicioService;

    @GetMapping("/producto/{producto}")
    public ResponseEntity<List<ServicioResponse>> getAllServiciosByProduct(@PathVariable Long producto){
        System.out.println("Producto recibido: " + producto);
        List<ServicioResponse> servicios = servicioService.getAllServiciosByProduct(producto);
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ServicioResponse>> getAllServicios() {
        List<ServicioResponse> servicios = servicioService.getAllServicios();
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Long id) {
        return servicioService.getServicioById(id)
                .map(servicio -> new ResponseEntity<>(servicio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Servicio> saveServicio(@RequestBody Servicio servicio) {
        Servicio savedServicio = servicioService.saveServicio(servicio);
        return new ResponseEntity<>(savedServicio, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Long id) {
        servicioService.deleteServicio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
