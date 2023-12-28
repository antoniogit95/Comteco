package comteco.backend.ordenDia.servicio;

import org.springframework.stereotype.Service;

import comteco.backend.ordenDia.OrdenDia;
import comteco.backend.ordenDia.OrdenDiaRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicioService {

    private ServicioRepository servicioRepository;
    private OrdenDiaRepository ordenDiaRepository;

    public List<ServicioResponse> getAllServicios() {
        List<Servicio> servicios = servicioRepository.findAll();
        List<ServicioResponse> response = new ArrayList<>();
        for(Servicio s: servicios){
            ServicioResponse sr = ServicioResponse.builder()
                .id(s.getId())
                .cod(s.getCod())
                .claseServicio(s.getClaseServicio())
                .numeroServicio(s.getNumeroServicio())
                .componente(s.getComponente())
                .areaServicio(s.getAreaServicio())
                .build();
            response.add(sr);
        }
        return response;
    }

    public Optional<Servicio> getServicioById(Long id) {
        return servicioRepository.findById(id);
    }

    public Servicio saveServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public void deleteServicio(Long id) {
        servicioRepository.deleteById(id);
    }

    public List<ServicioResponse> getAllServiciosByProduct(Long producto) {
        Optional<OrdenDia> ordenDia = ordenDiaRepository.findByProducto(producto);
        if(!ordenDia.isPresent()){
            return null;
        }
        List<Servicio> servicios = servicioRepository.findAllByOrdenDia(ordenDia.get());
        List<ServicioResponse> response = new ArrayList<>();
        for(Servicio s: servicios){
            ServicioResponse sr = ServicioResponse.builder()
                .id(s.getId())
                .cod(s.getCod())
                .claseServicio(s.getClaseServicio())
                .numeroServicio(s.getNumeroServicio())
                .componente(s.getComponente())
                .areaServicio(s.getAreaServicio())
                .build();
            response.add(sr);
        }
        return response;
    }
}
