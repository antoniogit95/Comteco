package comteco.backend.ordenDia.vendedor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * Servicios de la clase Vendedor
 */
@Service
@AllArgsConstructor
public class VendedorService {

    private VendedorRepository vendedorRepository;

    /**
     * @return retorna una lista de todos los vendores encontrados en la base de datos.
     */
    public List<Vendedor> getAllVendedors() {
        return vendedorRepository.findAll();
    }

    /**
     * 
     * @param id del vendor a buscar en la base de datos
     * @return un Objeto de tipo Optional que puedo o no un Vendedor
     */
    public Optional<Vendedor> getVendedorById(Long id) {
        return null;
    }

    /**
     * 
     * @param vendedor a ser guardado en la Base de datos
     * @return el vendor ya creado en la base de datos
     */
    public Vendedor saveVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    /**
     * 
     * @param id del vendedor a ser elimando de la base de datos
     */
    public void deleteVendedor(Long id) {
        vendedorRepository.deleteById(id);
    }
    
}
