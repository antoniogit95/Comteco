package comteco.backend.planComercial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlanComercialService {

    private PlanComercialRepository plComercialRepository;
    
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
                String[] partes = linea.split(";"); 
                System.out.println(partes.length+"---------------------"+partes[0]+"---------------");
                if (partes.length == 28) { 
                    PlanComercial pComercial = PlanComercial.builder()
                    .planComercial(partes[0])
                    .codLab(partes[1])
                    .codigoPlanComercial(partes[2])
                    .nuevoNombre(partes[3])
                    .codigoAlternativa(partes[4])
                    .tipoPlan(partes[5])
                    .nuevaVelocidad(partes[6])
                    .tipoEquipo(partes[7])
                    .planCorto(partes[8])
                    .nuevaTarifa(partes[9])
                    .CorrespondeInternet(partes[10])
                    .tarifa1(partes[11])
                    .tarifa2(partes[12])
                    .tarifa3(partes[13])
                    .fechaCreacion(partes[14])
                    .codigoHelpDesk(partes[15])
                    .kbpsDownInternet(partes[16])
                    .kbpsUpInternet(partes[17])
                    .kbpsDown8To20(partes[18])
                    .kbpsUp8To20(partes[19])
                    .kbpsDown20To8(partes[20])
                    .kbpsUp20To8(partes[21])
                    .kbpsDownRedesSociales(partes[22])
                    .kbpsUpRedesSociales(partes[23])
                    .kbpsDown4K(partes[24])
                    .kbpsUp4K(partes[25])
                    .incrementoMbps(partes[26])
                    .notas(partes[27])
                    .build();
                    plComercialRepository.save(pComercial);
                }
            }
            return new ResponseEntity<>("DatosGuardatosExitosamente", HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>("ErrorInesperador", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<PlanComercial>> getAllPlanComercial() {
        try {
            List<PlanComercial> planCOptional = plComercialRepository.findAll();
            return new ResponseEntity<>(planCOptional, HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
    }

}