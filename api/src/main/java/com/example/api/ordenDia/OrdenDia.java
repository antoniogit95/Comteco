package com.example.api.ordenDia;

import java.sql.Timestamp;

import com.example.api.Routes.Pos.Pos;
import com.example.api.ordenDia.servicio.Servicio;
import com.example.api.ordenDia.solicitud.Solicitud;
import com.example.api.ordenDia.trabajo.Trabajo;
import com.example.api.ordenDia.ubicacionTecnica.UbicacionTecnica;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "id_solicitud")
    private Solicitud solicitud;

    @ManyToOne
    @JoinColumn(name = "id_trabajo")
    private Trabajo trabajo;

    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion_tecnica")
    private UbicacionTecnica ubicacionTecnica;

    @Column(unique = true)
    private Long producto;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_pos")
    private Pos pos;
    private Long idCliente;
    private boolean estadoOt;
    private Long idTecnico;
}
