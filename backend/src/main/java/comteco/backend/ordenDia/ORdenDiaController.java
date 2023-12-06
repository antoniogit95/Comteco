package comteco.backend.ordenDia;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orden_dia")
public class ORdenDiaController {
    
    private  OrdenDiaService ordenDiaService;

    @PostMapping("/save_file")
    public ResponseEntity<String> saveOrdenDiaFile(@RequestParam("file") MultipartFile file) {
        if(file != null){
            return ordenDiaService.saveFile(file);
        }else{
            return new ResponseEntity<>("el archivo esta vacio", HttpStatus.ACCEPTED);
        }
    }
    

    @GetMapping
    public ResponseEntity<List<OrdenDia>> getAllOrdenDias() {
        List<OrdenDia> ordenDias = ordenDiaService.getAllOrdenDias();
        return new ResponseEntity<>(ordenDias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDia> getOrdenDiaById(@PathVariable Long id) {
        return ordenDiaService.getOrdenDiaById(id)
                .map(ordenDia -> new ResponseEntity<>(ordenDia, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<OrdenDia> saveOrdenDia(@RequestBody OrdenDia ordenDia) {
        OrdenDia savedOrdenDia = ordenDiaService.saveOrdenDia(ordenDia);
        return new ResponseEntity<>(savedOrdenDia, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdenDia(@PathVariable Long id) {
        ordenDiaService.deleteOrdenDia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenDia> editOrdenDia(@PathVariable String id, @RequestBody OrdenDia ordenDia) { 
        return new ResponseEntity<>(ordenDia, HttpStatus.OK);
    }
}
