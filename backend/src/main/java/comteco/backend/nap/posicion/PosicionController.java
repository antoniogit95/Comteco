package comteco.backend.nap.posicion;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/naps/pos")
public class PosicionController {

    private PosicionService posicionService;

    @PostMapping("/save_file")
    public ResponseEntity<String> saveNapsFile(@RequestParam("file") MultipartFile file) {
        if(file != null){;
            String response =  posicionService.saveFile(file);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("el archivo esta vacio", HttpStatus.ACCEPTED);
        }
    }

    @GetMapping
    public ResponseEntity<List<Posicion>> getAllPosiciones() {
        List<Posicion> posiciones = posicionService.getAllPosiciones();
        return new ResponseEntity<>(posiciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Posicion> getPosicionById(@PathVariable Long id) {
        return posicionService.getPosicionById(id)
                .map(posicion -> new ResponseEntity<>(posicion, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Posicion> savePosicion(@RequestBody Posicion posicion) {
        Posicion savedPosicion = posicionService.savePosicion(posicion);
        return new ResponseEntity<>(savedPosicion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosicion(@PathVariable Long id) {
        posicionService.deletePosicion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
