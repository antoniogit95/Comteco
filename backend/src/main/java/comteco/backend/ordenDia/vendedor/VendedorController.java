package comteco.backend.ordenDia.vendedor;

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
@RequestMapping("/api/v1/orden_dia/vendedor")
public class VendedorController {
    
    private VendedorService vendedorService;

    /**
     * @return Retorna una lista de todos los vendedores registrados
     */
    @GetMapping
    public ResponseEntity<List<Vendedor>> getAllVendedor(){
        List<Vendedor> vendedors = vendedorService.getAllVendedors();
        return new ResponseEntity<>(vendedors, HttpStatus.OK);
    }

    /**
     * 
     * @param id del Vendedora a buscar en la base de datos
     * @return el vendedor en caso de existir, caso contrario retornar null.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> getVendedorById(@PathVariable Long id){
        return vendedorService.getVendedorById(id)
            .map(vendedor -> new ResponseEntity<>(vendedor, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * @param vendedor vendedor a ser gurdado o registrado en la base de datos
     * @return un objeto del vendedor ya creado
     */
    @PostMapping
    public  ResponseEntity<Vendedor> saveVendedor(@RequestBody Vendedor vendedor){
        Vendedor savedVendedor = vendedorService.saveVendedor(vendedor);
        return new ResponseEntity<>(savedVendedor, HttpStatus.CREATED);
    }

    /**
     * 
     * @param id del Vendedor a ser Eliminado
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable Long id){
        vendedorService.deleteVendedor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
