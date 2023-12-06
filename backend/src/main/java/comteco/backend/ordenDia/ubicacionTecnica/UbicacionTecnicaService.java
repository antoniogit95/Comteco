package comteco.backend.ordenDia.ubicacionTecnica;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UbicacionTecnicaService {
    
    private UbicacionTecnicaRepository ubicacionTecnicaRepository;

    public List<UbicacionTecnica> getAllUbicacionesTecnicas() {
        return ubicacionTecnicaRepository.findAll();
    }

    public Optional<UbicacionTecnica> getUbicacionTecnicaById(Long id) {
        return ubicacionTecnicaRepository.findById(id);
    }

    public UbicacionTecnica saveUbicacionTecnica(UbicacionTecnica ubicacionTecnica) {
        return ubicacionTecnicaRepository.save(ubicacionTecnica);
    }

    public void deleteUbicacionTecnica(Long id) {
        ubicacionTecnicaRepository.deleteById(id);
    }

}
