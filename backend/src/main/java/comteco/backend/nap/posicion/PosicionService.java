package comteco.backend.nap.posicion;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PosicionService {
    
    private PosicionRepository posicionRepository;

    public List<Posicion> getAllPosiciones() {
        return posicionRepository.findAll();
    }

    public Optional<Posicion> getPosicionById(Long id) {
        return posicionRepository.findById(id);
    }

    public Posicion savePosicion(Posicion posicion) {
        return posicionRepository.save(posicion);
    }

    public void deletePosicion(Long id) {
        posicionRepository.deleteById(id);
    }
}
