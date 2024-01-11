package comteco.backend.nap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

/**
 * Servicios de la clase Nap
 */
@Service
@AllArgsConstructor
public class NapService {

    private NapRepository napRepository;


    /**
     * Lista todas las Naps
     * @return
     */
    public List<Nap> getAllNAPs() {
        return napRepository.findAll();
    }

    /**
     * 
     * @param id Nap a ser buscada en la base de datos
     * @return un Objeto de tipo Optional que puede o no tener una NAp
     */
    public Optional<Nap> getNAPById(Long id) {
        return napRepository.findById(id);
    }

    /**
     * 
     * @param napa a ser guardada en la base de datos
     * @return la misma nap creada.
     */
    public Nap saveNAP(Nap nap) {
        return napRepository.save(nap);
    }

    /**
     * 
     * @param id a ser Borrada.
     */
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

    /**
     * 
     * @return devuelve una lista de todos los cod --> codigos de la tabla naps
     */
    public List<String> getAllCods(){
        List<String> response = new ArrayList<>();
        try {
            List<Nap> naps = napRepository.findAll(); 
            for (Nap nap : naps) {
                response.add(nap.getCod());
            }
        } catch (Exception e) {
            response.add(e+"");
        }
        return response;
    }
}
