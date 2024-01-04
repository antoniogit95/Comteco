package comteco.backend.nap.posicion;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

/**
 * Controlador de la clase posicion
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/naps/pos")
public class PosicionController {

    private PosicionService posicionService;

    /**
     * 
     * @param file donde se encuntran todas las rutas naps y posiciones con sus codigos respectivos
     * @return un String donde se lista el estado al momento de gurdar las rutas naps
     */
    @PostMapping("/save_file")
    public ResponseEntity<String> saveNapsFile(@RequestParam("file") MultipartFile file) {
        if(file != null){;
            String response =  posicionService.saveFile(file);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("el archivo esta vacio", HttpStatus.ACCEPTED);
        }
    }

    /**
     * 
     * @return una Lista de todas las posiciones registradas 
     */
    @GetMapping
    public ResponseEntity<List<Posicion>> getAllPosiciones() {
        List<Posicion> posiciones = posicionService.getAllPosiciones();
        return new ResponseEntity<>(posiciones, HttpStatus.OK);
    }

    /**
     * 
     * @param id de la posicion a ser buscada y retornada
     * @return una posicion si existe caso contrario no retorna nada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Posicion> getPosicionById(@PathVariable Long id) {
        return posicionService.getPosicionById(id)
                .map(posicion -> new ResponseEntity<>(posicion, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 
     * @param posicion a ser gurdada en la base de datos
     * @return la misma posicion pero ya creada.
     */
    @PostMapping
    public ResponseEntity<Posicion> savePosicion(@RequestBody Posicion posicion) {
        Posicion savedPosicion = posicionService.savePosicion(posicion);
        return new ResponseEntity<>(savedPosicion, HttpStatus.CREATED);
    }
}
