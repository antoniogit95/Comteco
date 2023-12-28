package comteco.backend.ordenDia.servicio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comteco.backend.ordenDia.OrdenDia;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long>{

    List<Servicio> findAllByOrdenDia(OrdenDia ordenDia);
    
}
