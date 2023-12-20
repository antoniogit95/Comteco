package comteco.backend.dataTecnico;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DataTecnicoService {

    private DataTecnicoRepository dataTecnicoRepository;

    public Optional<DataTecnico> getDataTecnicoById(Long id) {
        return dataTecnicoRepository.findById(id);
    }

    public DataTecnico saveDataTecnico(DataTecnico dataTecnico) {
        return dataTecnicoRepository.save(dataTecnico);
    }
    
}
