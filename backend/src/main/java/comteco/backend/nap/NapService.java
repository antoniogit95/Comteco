package comteco.backend.nap;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
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

    
}
