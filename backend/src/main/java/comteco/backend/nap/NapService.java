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

    /**
     * @param cod es el codigo de una caja nap para preguntar si existe en la base de datos
     * @return en caso que exista retornara con un verdadero en caso de no existir retornara con falso
     */
    public boolean isExistCodNap(String cod){
        return napRepository.existsByCod(cod);
    }


    /**
     * 
     * @param cod buscar una caja nap por su codigo
     * @return una nap en caso de no encontrar retornar null
     */
    public Nap getNapByCod(String cod) {
        Optional<Nap> nOptional= napRepository.findByCod(cod);
        if(nOptional.isPresent()){
            return nOptional.get();
        }else{
            return null;
        }
    }
}
