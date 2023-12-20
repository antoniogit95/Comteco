package comteco.backend.ordenDia.cliente;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orden_dia/cliente")
public class ClienteController {

    private ClienteService clienteService;

    /**
     * 
     * @return una lista de tolos los clientes registrados 
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    /**
     * 
     * @param id del cliente que se desea obtener
     * @return un cliente en caso queexista caso contrario no retornar nada
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteoById(@PathVariable Long id) {
        return clienteService.getClienteById(id)
                .map(servicio -> new ResponseEntity<>(servicio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * @param cliente un cliente a ser guardado en la base de datos
     * @return al mismo cliente ya creado
     */
    @PostMapping
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente){
        Cliente savedCliente = clienteService.saveCliente(cliente);
        return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
    }

    /**
     * 
     * @param id del cliente que se desee eliminar
     * @return no retorna nada
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
