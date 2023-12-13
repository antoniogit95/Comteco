package comteco.backend.nap.posicion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import comteco.backend.nap.Nap;
import comteco.backend.nap.NapService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PosicionService {
    
    private PosicionRepository posicionRepository;

    private NapService napService;

    public List<Posicion> getAllPosiciones() {
        return posicionRepository.findAll();
    }

    public Optional<Posicion> getPosicionById(Long id) {
        return posicionRepository.findById(id);
    }

    public Posicion savePosicion(Posicion posicion) {
        return posicionRepository.save(posicion);
    }

    public void deletePosicion(Long id) {
        posicionRepository.deleteById(id);
    }

    public String saveFile(@RequestParam("file") MultipartFile file) {
        String response = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String linea;
            boolean firstLine = true;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                i++;
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                String partes[] = linea.split("[,;|]");
                if(partes.length == 5){
                    Nap nap = Nap.builder()
                        .odf(partes[0].toUpperCase())
                        .fdt(verificarValor(partes[1]).toUpperCase())
                        .nap(verificarValor(partes[2].toUpperCase()))
                        .cod(partes[3].toUpperCase())
                        .estado(true)
                        .build();
                    Nap responseNap = napService.saveNAP(nap);
                    response += "fila: "+i+"nap cargada con exito: "+responseNap.getCod();
                    Posicion posicion = Posicion.builder()
                        .cod(generatePosFosEspace(partes[4]+""))
                        .nap(responseNap)
                        .estado(false)
                        .build();
                    Posicion responsePos = posicionRepository.save(posicion);
                    response += " Posicion cargada con exito: "+ responsePos.getCod()+"\n";
                }
            }
        } catch (Exception e) {
            response += "ERROR AL CARGAR ARCHIVOS" + e;
        }
        return response;
    }

    /**
     * 
     * @param s recive un cadena de string para verificar si es un numero aumetar un 0 adelante
     * @return en caso de ser un numero retornara con un 0 por delante como ser: 1 --> 01
     */
    private String verificarValor(String s){
        if(s.length() == 1 && Character.isDigit(s.charAt(0))){
            return "0"+s;
        }
        return s;
    }


    /**
     * @param pos se ingresea una cadena que es una posicion y debe tener una longitud igual a 4 
     * @return en caso no tener una longitud de 4 aumentara ceros por delante
     */
    private String generatePosFosEspace(String pos){
        while (pos.length() < 4) {
            pos += "0"+pos;
        }   
        return pos;
    }
}
