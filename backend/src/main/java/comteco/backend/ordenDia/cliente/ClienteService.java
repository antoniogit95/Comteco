package comteco.backend.ordenDia.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * Servicios de la clase Cliente
 */
@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;

    /**
     * 
     * @return todos los clientes encontrados en la base de datos
     */
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    /**
     * 
     * @param id del cliente a buscar en la base de datos
     * @return un cliente de tipo Optional que puede o no tener un cliente
     */
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    /**
     * @param cliente un cliente a ser guardado en la Base de dato
     * @return retorna el mismo cliente ya creado
     */
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * 
     * @param id del cliente a ser eliminado
     */
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    
}
