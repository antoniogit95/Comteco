package com.example.api.Register;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.api.Person.Person;
import com.example.api.Person.PersonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DataTecnicoService {

    private final DataTecnicoRepository dataTecnicoRepository;
    private final PersonRepository personRepository;

    public DataTecnico getDataTecnicoById(Long id) {
        return dataTecnicoRepository.findById(id).orElse(null);
    }

    public List<DataTecnico> getAllDataTecnicos() {
        return dataTecnicoRepository.findAll();
    }

    public DataTecnico updateDataTecnico(Long id, DataTecnico dataTecnico) {
        if (dataTecnicoRepository.existsById(id)) {
            return dataTecnicoRepository.save(dataTecnico);
        }
        return null;
    }

    public boolean deleteDataTecnico(Long id) {
        if (dataTecnicoRepository.existsById(id)) {
            dataTecnicoRepository.deleteById(id);
            return true;
        }
        return false;
    }

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
}
