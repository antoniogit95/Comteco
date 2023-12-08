package comteco.backend.ordenDia;

import java.sql.Timestamp;

import comteco.backend.nap.posicion.Posicion;
import comteco.backend.ordenDia.servicio.Servicio;
import comteco.backend.ordenDia.solicitud.Solicitud;
import comteco.backend.ordenDia.trabajo.Trabajo;    
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrdenDia {
    
    @Id
    @GeneratedValue
    private Long id;

    private Timestamp fecha;

    @OneToOne
    @JoinColumn(name = "id_solicitud")
    private Solicitud solicitud;
    
    @OneToOne
    @JoinColumn(name = "id_trabajo")
    private Trabajo trabajo;

    @OneToOne
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;

    private String ubicacion;

    private Long producto;

    private String contrato;
    private boolean estado;
    private String orden;

    @OneToOne
    @JoinColumn(name = "id_posicion")
    private Posicion posicion;

    private String descripcion;
    private String actividad;
    private String codUnidad;
    private String unidadOperativa;
    private String cliente;
    private String direccion;
    private String tipoCliente; 
    private String puntoVenta;
    private String estadoOt;
    private String vendedor;

}
