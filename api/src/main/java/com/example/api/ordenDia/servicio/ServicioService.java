package com.example.api.ordenDia.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicioService {

    private ServicioRepository servicioRepository;

    public List<Servicio> getAllServicios() {
        return servicioRepository.findAll();
    }

    public Servicio getServicioById(Long id) {
        Optional<Servicio> sOptional = servicioRepository.findById(id);
        return sOptional.isPresent()? sOptional.get() : null;
    }

    public Servicio createServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Servicio updateServicio(Long id, Servicio servicio) {
        Optional<Servicio> sOptional = servicioRepository.findById(id);
        if (sOptional.isPresent()) {
            servicio.setId(id);
            servicioRepository.save(servicio);
            return servicio;
        }else{
            return null;
        }
    }

    public Boolean deleteServicio(Long id) {
        Optional<Servicio> sOptional = servicioRepository.findById(id);
        if(sOptional.isPresent()){
            servicioRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    
}
