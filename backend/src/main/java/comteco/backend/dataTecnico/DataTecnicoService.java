package comteco.backend.dataTecnico;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import comteco.backend.nap.posicion.Posicion;
import comteco.backend.nap.posicion.PosicionService;
import comteco.backend.user.User;
import comteco.backend.user.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DataTecnicoService {

    private DataTecnicoRepository dataTecnicoRepository;
    private UserRepository userRepository;
    private PosicionService posicionService;

    public Optional<DataTecnico> getDataTecnicoById(Long id) {
        return dataTecnicoRepository.findById(id);
    }

    /**
     * Guarda el nuevo dato tecnico 
     * @param dataTecnicoRequest
     * @return
     */
    public DataTecnico saveDataTecnico(DatoTecnicoRequest dataTecnicoRequest) {
        try {
            Optional<User> user = userRepository.findByUsername(dataTecnicoRequest.getUsername());
            System.out.println("Usuario Rescatado");
            Posicion nuevaPosicion = posicionService.saveNapAndPos(dataTecnicoRequest.getNuevoNap());
            System.out.println("nueva posicion Rescatado");
            Posicion antiguaPosicion = posicionService.saveNapAndPos(dataTecnicoRequest.getAntogupNap());
            System.out.println("Antogua Posicion Rescatado");
            System.out.println("PRODUCTO: "+ dataTecnicoRequest.getProducto());
            DataTecnico dataTecnico = DataTecnico.builder()
                .producto(dataTecnicoRequest.getProducto())
                .user(user.get())
                .nuevaPosicion(nuevaPosicion)
                .antiguaPosicion(antiguaPosicion)
                .obeservaciones(dataTecnicoRequest.getObservaciones())
                .created_at(getTimestamp())
                .update_at(getTimestamp())
                .build();
            System.out.println("dato tecnico CREADO");
            DataTecnico saveDataTecnico = dataTecnicoRepository.save(dataTecnico);
            System.out.println("dato tecnico Guardado");
            return saveDataTecnico;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    /**
     * @return Obtine la hora actual del sistema
     */
    private Timestamp getTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now);
    }

}
