package comteco.backend.ordenDia.trabajo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrabajoService {
    
    private TrabajoRepository trabajoRepository;

    public List<Trabajo> getAllTrabajos() {
        return trabajoRepository.findAll();
    }

    public Optional<Trabajo> getTrabajoById(Long id) {
        return trabajoRepository.findById(id);
    }

    public Trabajo saveTrabajo(Trabajo trabajo) {
        return trabajoRepository.save(trabajo);
    }

    public void deleteTrabajo(Long id) {
        trabajoRepository.deleteById(id);
    }
}
