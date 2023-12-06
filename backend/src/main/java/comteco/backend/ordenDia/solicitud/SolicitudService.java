package comteco.backend.ordenDia.solicitud;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SolicitudService {
 
    private SolicitudRepository solicitudRepository;

    public List<Solicitud> getAllSolicitudes() {
        return solicitudRepository.findAll();
    }

    public Optional<Solicitud> getSolicitudById(Long id) {
        return solicitudRepository.findById(id);
    }

    public Solicitud saveSolicitud(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    public void deleteSolicitud(Long id) {
        solicitudRepository.deleteById(id);
    }
}
