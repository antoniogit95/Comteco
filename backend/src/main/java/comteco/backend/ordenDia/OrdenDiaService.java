package comteco.backend.ordenDia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import comteco.backend.ordenDia.planComercial.PlanComercial;
import comteco.backend.ordenDia.solicitud.Solicitud;
import comteco.backend.ordenDia.trabajo.Trabajo;
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
                String partes[] = linea.split(";");
                System.out.println(partes.length);
                if(partes.length == 29){
                    OrdenDia ordenDia = OrdenDia.builder()
                        .fecha(getFecha(partes[0]))
                        .build();
                    System.out.println(partes[0]+" --> "+ordenDia.getFecha());
                    Solicitud solicitud = Solicitud.builder()
                        .codTipoSol(Long.parseLong(partes[1]))
                        .tipoSolicitud(partes[2])
                        .build();
                    System.out.println("CLASE SOLICITUD");
                    System.out.println(partes[1]+" --> "+solicitud.getCodTipoSol());
                    System.out.println(partes[2]+" --> "+solicitud.getTipoSolicitud());
                    
                    System.out.println("CLASE TRABAJO");
                    Trabajo trabajo = Trabajo.builder()
                        .codTipo(Long.parseLong(partes[3]))
                        .tipoTrabajo(partes[4])
                        .build();
                    System.out.println(partes[3]+" --> "+trabajo.getCodTipo());
                    System.out.println(partes[4]+" --> "+trabajo.getTipoTrabajo());
                    
                    System.out.println("CLASE PLAN COMERCIAL");
                    PlanComercial planComercial = PlanComercial.builder()
                        .codLab(partes[5]+"-")
                        .planCorto(partes[6])
                        .build();
                    System.out.print(partes[5]+" -->"+planComercial.getCodLab());
                    System.out.print(partes[6]+" -->"+planComercial.getPlanCorto());
                    System.out.print(partes[7]+" ");
                    System.out.print(partes[8]+" ");
                    System.out.print(partes[9]+" ");
                    System.out.print(partes[10]+" ");
                    System.out.print(partes[11]+" ");
                    System.out.print(partes[12]+" ");
                    System.out.print(partes[13]+" ");
                    System.out.print(partes[14]+" ");
                    System.out.print(partes[15]+" ");
                    System.out.print(partes[16]+" ");
                    System.out.print(partes[17]+" ");
                    System.out.print(partes[18]+" ");
                    System.out.print(partes[19]+" ");
                    System.out.print(partes[20]+" ");
                    System.out.print(partes[21]+" ");
                    System.out.print(partes[22]+" ");
                    System.out.print(partes[23]+" ");
                    System.out.print(partes[24]+" ");
                    System.out.print(partes[25]+" ");
                    System.out.print(partes[26]+" ");
                    System.out.print(partes[27]+" ");
                    System.out.print(partes[28]+" ");
                    System.out.println("");
                    br = null;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    private Timestamp getFecha(String date){
        try {
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = formatFecha.parse(date);
            return new Timestamp(fecha.getTime());
        } catch (Exception e) {
            return null;
        }
    }
}
