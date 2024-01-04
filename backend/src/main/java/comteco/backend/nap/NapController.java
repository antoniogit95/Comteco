package comteco.backend.nap;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador de las rutas Naps
 */
@RestController
@RequestMapping("/api/v1/naps")
@AllArgsConstructor
public class NapController {
        
    private NapService napService;
    
    /**
     * 
     * @return Una lista de todas las naps 
     */
    @GetMapping
    public ResponseEntity<List<Nap>> getAllNAPs() {
        List<Nap> naps = napService.getAllNAPs();
        return new ResponseEntity<>(naps, HttpStatus.OK);
    }

    /**
     * 
     * @param id de la Nap a buscar en la base de datos
     * @return una nap en caso que exista, caso contrario retorna notfound
     */
    @GetMapping("/{id}")
    public ResponseEntity<Nap> getNAPById(@PathVariable Long id) {
        return napService.getNAPById(id)
                .map(nap -> new ResponseEntity<>(nap, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * @param nap a ser gurdada en la base de datos
     * @return la misna nap guardada
     */
    @PostMapping
    public ResponseEntity<Nap> saveNAP(@RequestBody Nap nap) {
        Nap savedNAP = napService.saveNAP(nap);
        return new ResponseEntity<>(savedNAP, HttpStatus.CREATED);
    }

    /**
     * 
     * @param id de la nap a ser borrada
     * @return no retorna nada.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNAP(@PathVariable Long id) {
        napService.deleteNAP(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 
     * @return devuelve una lista de todos los cod --> codigos de la tabla naps
     */
    @GetMapping("/cods")
    public ResponseEntity<List<String>> getAllNapsByCod() {
        List<String> napCods  = napService.getAllCods();
        return new ResponseEntity<>(napCods, HttpStatus.OK);
     }
    
}
