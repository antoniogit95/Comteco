package comteco.backend.dataTecnico;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import comteco.backend.nap.posicion.Posicion;
import comteco.backend.nap.posicion.PosicionService;
import comteco.backend.ordenDia.OrdenDia;
import comteco.backend.ordenDia.OrdenDiaRepository;
import comteco.backend.user.User;
import comteco.backend.user.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DataTecnicoService {

    private DataTecnicoRepository dataTecnicoRepository;
    private UserRepository userRepository;
    private PosicionService posicionService;
    private OrdenDiaRepository ordenDiaRepository;

    public Optional<DataTecnico> getDataTecnicoById(Long id) {
        return dataTecnicoRepository.findById(id);
    }

    /**
     * Guarda el nuevo dato tecnico 
     * @param dataTecnicoRequest a ser guardado
     * @return un objeto DatoTecnicoResponse que ahi manejamos los errores en caso de tenerlos
     */
    public ResponseEntity<DataTecnicoResponse> saveDataTecnico(DatoTecnicoRequest dataTecnicoRequest) {
        DataTecnicoResponse dataTecnicoResponse;
        try {
            Posicion nuevaPosicion = posicionService.saveNapAndPos(dataTecnicoRequest.getNuevoNap());
            Optional<OrdenDia> ordeOptional = ordenDiaRepository.findByPosicion(nuevaPosicion);
            if(ordeOptional.isPresent()){
                 return ResponseEntity.status(HttpStatus.CONFLICT).body(DataTecnicoResponse.builder()
                .message("Posicion: "+nuevaPosicion.getNap().getCod()+"-"+nuevaPosicion.getCod()+" ya asignada a la Orden: "+ordeOptional.get().getProducto())
                .build());
            }else{
                Optional<User> user = userRepository.findByUsername(dataTecnicoRequest.getUsername());
                System.out.println("Usuario Rescatado");
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
                //Actulizar la Direccion Nap a la Orden Dia
                OrdenDia ordenDia = ordenDiaRepository.findByProducto(dataTecnicoRequest.getProducto()).get();
                ordenDia.setPosicion(nuevaPosicion);
                ordenDiaRepository.save(ordenDia);
                dataTecnicoResponse = DataTecnicoResponse.builder()
                    .id(saveDataTecnico.getId())
                    .username(saveDataTecnico.getUser().getUsername())
                    .producto(saveDataTecnico.getProducto())
                    .nuevaPosicion(saveDataTecnico.getNuevaPosicion().getNap().getCod()+"-"+saveDataTecnico.getNuevaPosicion().getCod())
                    .antiguaPosicion(saveDataTecnico.getAntiguaPosicion().getNap().getCod()+"-"+saveDataTecnico.getAntiguaPosicion().getCod())
                    .createdAt(saveDataTecnico.getCreated_at())
                    .updateAt(saveDataTecnico.getUpdate_at())
                    .build();
                return new ResponseEntity<>(dataTecnicoResponse, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            dataTecnicoResponse = DataTecnicoResponse.builder()
                .message("Error: "+e)
                .build();
            return new ResponseEntity<>(dataTecnicoResponse, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return Obtine la hora actual del sistema
     */
    private Timestamp getTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now);
    }

    /**
     * Metodo para obtener todos los datos tecnicos de la base de datos;
     * @return
     */
    public List<DatoTecnicoReportResponse> getAllDatoTecnico() {
        List<DataTecnico> dataTecnicos = dataTecnicoRepository.findAll();
        List<DatoTecnicoReportResponse> responses = new ArrayList<>();
        for (DataTecnico dt : dataTecnicos) {
            DatoTecnicoReportResponse dtrr = DatoTecnicoReportResponse.builder()
                .id(dt.getId())
                .nombreCompleto(dt.getUser().getPerson().getNombre()+" "+dt.getUser().getPerson().getApellidos())
                .producto(dt.getProducto())
                .nuevaPosicion(dt.getNuevaPosicion().getNap().getCod()+"-"+dt.getNuevaPosicion().getCod())
                .antiguaPosicion(dt.getAntiguaPosicion().getNap().getCod()+"-"+dt.getAntiguaPosicion().getCod())
                .createdAt(dt.getCreated_at())
                .updateAt(dt.getUpdate_at())
                .build();
            responses.add(dtrr);
        }

        return responses;
    }

    public List<DatoTecnicoReportResponse> getAllCambiosPos() {
        List<DataTecnico> dataTecnicos = dataTecnicoRepository.findAll();
        List<DatoTecnicoReportResponse> responses = new ArrayList<>();
        for (DataTecnico dt : dataTecnicos) {
            if(dt.getAntiguaPosicion().getNap().getCod().equals(dt.getNuevaPosicion().getNap().getCod()) && !dt.getAntiguaPosicion().getCod().equals(dt.getNuevaPosicion().getCod())){
                DatoTecnicoReportResponse dtrr = DatoTecnicoReportResponse.builder()
                .id(dt.getId())
                .nombreCompleto(dt.getUser().getPerson().getNombre()+" "+dt.getUser().getPerson().getApellidos())
                .producto(dt.getProducto())
                .nuevaPosicion(dt.getNuevaPosicion().getNap().getCod()+"-"+dt.getNuevaPosicion().getCod())
                .antiguaPosicion(dt.getAntiguaPosicion().getNap().getCod()+"-"+dt.getAntiguaPosicion().getCod())
                .createdAt(dt.getCreated_at())
                .updateAt(dt.getUpdate_at())
                .build();
                responses.add(dtrr);
            }
        }
        return responses;
    }

    public List<DatoTecnicoReportResponse> getAllCambiosNap() {
        List<DataTecnico> dataTecnicos = dataTecnicoRepository.findAll();
        List<DatoTecnicoReportResponse> responses = new ArrayList<>();
        for (DataTecnico dt : dataTecnicos) {
            if(dt.getAntiguaPosicion().getNap().getNap() == null || dt.getNuevaPosicion().getNap().getNap() == null){
            }else{
                String anCodFdt = dt.getAntiguaPosicion().getNap().getOdf()+"-"+dt.getAntiguaPosicion().getNap().getFdt();
                String nuCodFdt = dt.getNuevaPosicion().getNap().getOdf()+"-"+dt.getNuevaPosicion().getNap().getFdt();
                String anCodNap = dt.getAntiguaPosicion().getNap().getNap();
                String nuCodNap = dt.getNuevaPosicion().getNap().getNap();
                if(anCodFdt.equals(nuCodFdt) && !anCodNap.equals(nuCodNap)){
                    DatoTecnicoReportResponse dtrr = DatoTecnicoReportResponse.builder()
                    .id(dt.getId())
                    .nombreCompleto(dt.getUser().getPerson().getNombre()+" "+dt.getUser().getPerson().getApellidos())
                    .producto(dt.getProducto())
                    .nuevaPosicion(dt.getNuevaPosicion().getNap().getCod()+"-"+dt.getNuevaPosicion().getCod())
                    .antiguaPosicion(dt.getAntiguaPosicion().getNap().getCod()+"-"+dt.getAntiguaPosicion().getCod())
                    .createdAt(dt.getCreated_at())
                    .updateAt(dt.getUpdate_at())
                    .build();
                    responses.add(dtrr);
                }
            }
        }
        return responses;
    }

    public List<DatoTecnicoReportResponse> getAllCambiosFdt() {
        List<DataTecnico> dataTecnicos = dataTecnicoRepository.findAll();
        List<DatoTecnicoReportResponse> responses = new ArrayList<>();
        for (DataTecnico dt : dataTecnicos) {
            if(dt.getAntiguaPosicion().getNap().getNap() == null || dt.getNuevaPosicion().getNap().getNap() == null){
            }else{
                String anCodOdf = dt.getAntiguaPosicion().getNap().getOdf();
                String nuCodOdf = dt.getNuevaPosicion().getNap().getOdf();
                String anCodFdt = dt.getAntiguaPosicion().getNap().getFdt();
                String nuCodFdt = dt.getNuevaPosicion().getNap().getFdt();
                if(anCodOdf.equals(nuCodOdf) && !anCodFdt.equals(nuCodFdt)){
                    DatoTecnicoReportResponse dtrr = DatoTecnicoReportResponse.builder()
                    .id(dt.getId())
                    .nombreCompleto(dt.getUser().getPerson().getNombre()+" "+dt.getUser().getPerson().getApellidos())
                    .producto(dt.getProducto())
                    .nuevaPosicion(dt.getNuevaPosicion().getNap().getCod()+"-"+dt.getNuevaPosicion().getCod())
                    .antiguaPosicion(dt.getAntiguaPosicion().getNap().getCod()+"-"+dt.getAntiguaPosicion().getCod())
                    .createdAt(dt.getCreated_at())
                    .updateAt(dt.getUpdate_at())
                    .build();
                    responses.add(dtrr);
                }
            }
        }
        return responses;
    }

    public List<DatoTecnicoReportResponse> getAllCambiosOdf() {
        List<DataTecnico> dataTecnicos = dataTecnicoRepository.findAll();
        List<DatoTecnicoReportResponse> responses = new ArrayList<>();
        for (DataTecnico dt : dataTecnicos) {
            if(dt.getAntiguaPosicion().getNap().getNap() == null || dt.getNuevaPosicion().getNap().getNap() == null){
            }else{
                String anCodOdf = dt.getAntiguaPosicion().getNap().getOdf();
                String nuCodOdf = dt.getNuevaPosicion().getNap().getOdf();
                if(!anCodOdf.equals(nuCodOdf)){
                    DatoTecnicoReportResponse dtrr = DatoTecnicoReportResponse.builder()
                    .id(dt.getId())
                    .nombreCompleto(dt.getUser().getPerson().getNombre()+" "+dt.getUser().getPerson().getApellidos())
                    .producto(dt.getProducto())
                    .nuevaPosicion(dt.getNuevaPosicion().getNap().getCod()+"-"+dt.getNuevaPosicion().getCod())
                    .antiguaPosicion(dt.getAntiguaPosicion().getNap().getCod()+"-"+dt.getAntiguaPosicion().getCod())
                    .createdAt(dt.getCreated_at())
                    .updateAt(dt.getUpdate_at())
                    .build();
                    responses.add(dtrr);
                }
            }
        }
        return responses;
    }

}
