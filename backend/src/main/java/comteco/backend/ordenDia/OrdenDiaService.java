package comteco.backend.ordenDia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrdenDiaService {

    private OrdenDiaRepository ordenDiaRepository;

    public List<OrdenDia> getAllOrdenDias() {
        return ordenDiaRepository.findAll();
    }

    public Optional<OrdenDia> getOrdenDiaById(Long id) {
        return ordenDiaRepository.findById(id);
    }

    public OrdenDia saveOrdenDia(OrdenDia ordenDia) {
        return ordenDiaRepository.save(ordenDia);
    }

    public void deleteOrdenDia(Long id) {
        ordenDiaRepository.deleteById(id);
    }

    public ResponseEntity<String> saveFile(MultipartFile file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String linea;
            boolean firstLine = true;
            while ((linea = br.readLine()) != null) {
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                String partes[] = linea.split(";");
                System.out.println(partes.length+"---------------------"+partes[0]+"---------------");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }
}
