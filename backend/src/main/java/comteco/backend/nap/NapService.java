package comteco.backend.nap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NapService {

    private NapRepository napRepository;

    public List<Nap> getAllNAPs() {
        return napRepository.findAll();
    }

    public Optional<Nap> getNAPById(Long id) {
        return napRepository.findById(id);
    }

    public Nap saveNAP(Nap nap) {
        return napRepository.save(nap);
    }

    public void deleteNAP(Long id) {
        napRepository.deleteById(id);
    }

    public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String linea;
            boolean firstLine = true;
            while ((linea = br.readLine()) != null) {
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                String partes[] = linea.split("[,;|]");
                System.out.println(partes.length);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}
