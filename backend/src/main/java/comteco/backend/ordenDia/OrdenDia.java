package comteco.backend.ordenDia;

import java.sql.Timestamp;
import java.util.List;

import comteco.backend.nap.posicion.Posicion;
import comteco.backend.ordenDia.cliente.Cliente;
import comteco.backend.ordenDia.servicio.Servicio;
import comteco.backend.ordenDia.solicitud.Solicitud;
import comteco.backend.ordenDia.trabajo.Trabajo;
import comteco.backend.ordenDia.vendedor.Vendedor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Enttidad de la tabla orden_dia
 */
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
    private String ubicacion;

    /*
     *Relacion la La tabla Solicitud donde una orden dia solo tiene una solicitud 
     */
    @OneToOne
    @JoinColumn(name = "id_solicitud")
    private Solicitud solicitud;
    
    /*
     * Relacion con la tabla trabajo que una orden dia solo tiene un trabajo
     */
    @OneToOne
    @JoinColumn(name = "id_trabajo")
    private Trabajo trabajo;

    @Column(unique = true)
    private Long producto;

    private String contrato;
    private boolean estadoOt; //estado_ot
    private String orden;

    /*
     * Relacion con la tabla posicion que asu ves tiene relacion con las posiciones nap
     * que un orden dia solo puede estar asociado a una posicion de las rutas nap
     */
    @OneToOne
    @JoinColumn(name = "id_posicion")
    private Posicion posicion;

    private String actividad;

    /*
     * Relacion con la tabla vendedor donde una orden dia solo puede tener un vendedor.
     */
    @OneToOne
    @JoinColumn(name = "id_vendedor")
    private Vendedor vendedor;

    /*
     * Relacion con la tabla Cliente donde una orden dia solo pueda tener a un cliente.
     */
    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    /*
     * Relacion con la talba de Servicion donde una orden dia tenga muchos servicios.
     */
    @OneToMany(mappedBy = "ordenDia", cascade = CascadeType.ALL)
    private List<Servicio> servicio;
}
