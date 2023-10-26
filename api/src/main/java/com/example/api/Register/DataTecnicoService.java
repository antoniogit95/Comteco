package com.example.api.Register;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.Person.Person;
import com.example.api.Person.PersonRepository;

import lombok.AllArgsConstructor;

/**
 * Servicio para la gestión de datos técnicos.
 */
@Service
@AllArgsConstructor
public class DataTecnicoService {

    private final DataTecnicoRepository dataTecnicoRepository;
    private final PersonRepository personRepository;

    /**
     * Obtiene datos técnicos por su ID.
     *
     * @param id El ID de los datos técnicos que se buscan.
     * @return Los datos técnicos si se encuentran, de lo contrario, devuelve null.
     */
    public DataTecnico getDataTecnicoById(Long id) {
        return dataTecnicoRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todos los datos técnicos disponibles.
     *
     * @return Una lista de todos los datos técnicos.
     */
    public List<DataTecnico> getAllDataTecnicos() {
        return dataTecnicoRepository.findAll();
    }

    /**
     * Actualiza datos técnicos por su ID con los detalles proporcionados.
     *
     * @param id El ID de los datos técnicos que se desean actualizar.
     * @param dataTecnico Los nuevos detalles de los datos técnicos.
     * @return Los datos técnicos actualizados si existen, de lo contrario, devuelve null.
     */
    public DataTecnico updateDataTecnico(Long id, DataTecnico dataTecnico) {
        if (dataTecnicoRepository.existsById(id)) {
            return dataTecnicoRepository.save(dataTecnico);
        }
        return null;
    }

    /**
     * Elimina datos técnicos por su ID.
     *
     * @param id El ID de los datos técnicos que se desean eliminar.
     * @return `true` si los datos técnicos se eliminan con éxito, de lo contrario, `false`.
     */
    public boolean deleteDataTecnico(Long id) {
        if (dataTecnicoRepository.existsById(id)) {
            dataTecnicoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Crea y registra nuevos datos técnicos con los detalles proporcionados.
     *
     * @param request Los detalles de los datos técnicos a crear.
     * @return Los datos técnicos creados y registrados, o null si la persona no existe.
     */
    public  DataTecnico createRegisterDataTecnico(DataTecnicoRequest request){
        Optional<Person> existingPerson = personRepository.findById(request.getId_person());
        if(existingPerson.isPresent()){
            Person person = existingPerson.get();
            System.out.println(request.getObservaciones());
            DataTecnico dataTecnico = DataTecnico.builder()
                .num_producto(request.getNum_producto())
                .caja_nap(request.getCaja_nap())
                .estadp_odt(request.getEstado_odt())
                .obasrvaciones(request.getObservaciones())
                .created_at(getTimestamp())
                .update_at(getTimestamp())
                .person(person)
                .build();
            dataTecnicoRepository.save(dataTecnico);
            return dataTecnico;
        }else{
            return null;
        }
    }

    private Timestamp getTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now);
    }

    /**
     * Guarda muchos datos tecnicos a travez de un archivo
     *
     * @param file El archivo de datos técnicos a guardar.
     * @return El resultado de la operación y un estado OK, o un respuesta de error si no se pudieron obtener los datos del archivo
     */
    public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile file){
        try {
            Long id = 1L;
            Optional<Person> existingPerson = personRepository.findById(id);
            Person person = existingPerson.get();
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                System.out.println(partes.length);
                if(partes.length == 7){
                    DataTecnico dataTecnico = DataTecnico.builder()
                        .num_producto(partes[3].trim())
                        .caja_nap(partes[0].trim())
                        .estadp_odt(partes[2].trim())
                        .obasrvaciones(partes[1].trim())
                        .created_at(getTimestamp())
                        .update_at(getTimestamp())
                        .person(person)
                        .build();
                    dataTecnicoRepository.save(dataTecnico);
                }
            }
            br.close();
            return ResponseEntity.ok("Archivo guardado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
