package comteco.backend.ordenDia;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrdenDiaService {

    //private OrdenDiaRepository ordenDiaRepository;

    public ResponseEntity<List<OrdenDiaResponse>> getAllOrdenDias() {
        try {
            //List<OrdenDia> ordenDia = ordenDiaRepository.findAll();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<OrdenDiaResponse> getOrdenDiaById(Long id) {
        return null;
    }

    public ResponseEntity<OrdenDia> createOrdenDia(OrdenDia ordenDia) {
        return null;
    }

    public ResponseEntity<OrdenDia> updateOrdenDia(Long id, OrdenDia ordenDia) {
        return null;
    }

    public void createOrdenDia(Long id) {
    }

    public ResponseEntity<Boolean> deleteOrdenDia(Long id) {
        return null;
    }
    
}
