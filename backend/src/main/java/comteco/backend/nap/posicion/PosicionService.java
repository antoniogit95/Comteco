package comteco.backend.nap.posicion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import comteco.backend.nap.Nap;
import comteco.backend.nap.NapRepository;
import comteco.backend.nap.NapService;
import lombok.AllArgsConstructor;

/**
 * Servicios de la clase Posicion
 */
@Service
@AllArgsConstructor
public class PosicionService {
    
    private PosicionRepository posicionRepository;
    private NapRepository napRepository;
    private NapService napService;

    /**
     * Lista todas las posicioes
     * @return Una Lista con todas las posicione registradas en la base de datos.
     */
    public List<Posicion> getAllPosiciones() {
        return posicionRepository.findAll();
    }

    /**
     * 
     * @param id de una Posicion a ser Buscada.
     * @return Un Objeto de tipo Optional que puede o no tener a una Posicion.
     */
    public Optional<Posicion> getPosicionById(Long id) {
        return posicionRepository.findById(id);
    }

    /**
     * 
     * @param posicion a ser guardada en la base de datos
     * @return la misma posicion ya creada.
     */
    public Posicion savePosicion(Posicion posicion) {
        return posicionRepository.save(posicion);
    }

    /**
     * 
     * @param id de la posicion a ser borrada.
     */
    public void deletePosicion(Long id) {
        posicionRepository.deleteById(id);
    }

    /**
     * @param napAndPos dato un String que tenga los datos de la nap y pos guardarlos
     * @return retorna el objeto creado 
     */
    public Posicion saveNapAndPos(String napAndPos) {
        Posicion posicion = null;
        if(napAndPos != null){
            String partes[] = napAndPos.split("[-/]");
                System.out.println(napAndPos+"tiene: "+partes.length);
                if(partes.length == 4){
                    Nap nap = Nap.builder()
                        .odf(partes[0].toUpperCase())
                        .fdt(verificarValor(partes[1]).toUpperCase())
                        .nap(verificarValor(partes[2].toUpperCase()))
                        .cod((partes[0]+"-"+partes[1]+"-"+partes[2]).toUpperCase())
                        .estado(true)
                        .build();
                    if(napService.isExistCodNap(nap.getCod())){
                        nap = napService.getNapByCod(nap.getCod());
                    }else{
                        nap = napService.saveNAP(nap);
                    }

                    posicion = Posicion.builder()
                        .cod(generatePosFosEspace(partes[3]+""))
                        .nap(nap)
                        .estado(true)
                        .build();
                    if(isExistCodNapAndPosicion(posicion.getCod(), nap)){
                        System.out.println("posicion y nap existentes");
                        posicion = posicionRepository.findByCodAndNap(posicion.getCod(), nap).get();
                    }else{
                        posicion = posicionRepository.save(posicion);
                    }
                }else if(partes.length == 2){
                    Nap nap = Nap.builder()
                        .cod((partes[0]).toUpperCase())
                        .estado(true)
                        .build();
                    if(napService.isExistCodNap(nap.getCod())){
                        nap = napService.getNapByCod(nap.getCod());
                    }else{
                        nap = napService.saveNAP(nap);
                    }
                    posicion = Posicion.builder()
                        .cod(generatePosFosEspace(partes[1]+""))
                        .nap(nap)
                        .estado(true)
                        .build();
                    if(isExistCodNapAndPosicion(posicion.getCod(), nap)){
                        System.out.println("POSICION YA EXISTENTE");
                        Optional<Posicion> pOptional = posicionRepository.findByCodAndNap(posicion.getCod(), nap);
                        if(pOptional.isPresent()){
                            posicion = pOptional.get();
                        }
                    }else{
                        System.out.println("GAURDADNDO POSICION");
                        posicion = posicionRepository.save(posicion);
                    }
                }
        }
        System.out.println(posicion.getNap().getCod()+"-"+posicion.getCod());
        return posicion;
    }

    /**
     * 
     * @param file recive un file de tipo csv que contriene todos los datos de la nap
     * @return una cadena con el historial de los datos guardados y los errores que tubo
     */
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
                    if(napService.isExistCodNap(nap.getCod())){
                        nap = napService.getNapByCod(nap.getCod());
                        response += "fila: "+i+"nap ya existe: "+nap.getCod();
                    }else{
                        nap = napService.saveNAP(nap);
                        response += "fila: "+i+"nap cargada con exito: "+nap.getCod();
                    }
                    Posicion posicion = Posicion.builder()
                        .cod(generatePosFosEspace(partes[4]+""))
                        .nap(nap)
                        .estado(false)
                        .build();
                    if(isExistCodNapAndPosicion(posicion.getCod(), nap)){
                        response += " Posicion ya existe: "+ posicion.getCod()+"\n";
                    }else{
                        posicion = posicionRepository.save(posicion);
                        response += " Posicion cargada con exito: "+ posicion.getCod()+"\n";
                    }
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
     * 
     * @param codPosicion a preguntar si existe en la caja nap.
     * @param nap caja nap de donde se preguntra si existe alguna posicion.
     * @return en caso que existan ambos componentes retornara true caso contrario false.
     */
    public boolean isExistCodNapAndPosicion( String codPosicion, Nap nap){
        return posicionRepository.existsByCodAndNap(codPosicion, nap);
    }

    /**
     * @param pos se ingresea una cadena que es una posicion y debe tener una longitud igual a 4 
     * @return en caso no tener una longitud de 4 aumentara ceros por delante
     */
    private String generatePosFosEspace(String pos){
        while (pos.length() < 4) {
            pos = "0"+pos;
        }   
        return pos;
    }
    /**
     * 
     * @param ruta_nap ruta para ser guardado en la caja nao
     * @param descripcion descriprion de la ruta o ubicacion
     * @return la misma posicion ya creada.
     */
    public Posicion saveNapAndPos(String ruta_nap, String descripcion) {
        Posicion pos = saveNapAndPos(ruta_nap);
        pos.getNap().setDescripcion(descripcion);
        System.out.println("imprmiendo la posicion"+ pos.getCod());
        return posicionRepository.save(pos);
    }

    /**
     * 
     * @param nap id de la nap a aser buscada en la base de datos
     * @return una lista de todas las posiciones relacionadas a la nap.
     */
    public List<Posicion> getAllPosicionesByNap(Long nap) {
        try {
            Optional<Nap> napOptional = napRepository.findById(nap);
            if (napOptional.isPresent()) {
                Nap nap2 = napOptional.get();
                List<Posicion> posicions = posicionRepository.findAllByNap(nap2);
                return posicions;
            }{
                return null;
            }
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
