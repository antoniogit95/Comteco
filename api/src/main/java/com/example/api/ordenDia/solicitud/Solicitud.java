package com.example.api.ordenDia.solicitud;

import com.example.api.PlanComercial.PlanComercial;

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
public class Solicitud {
    
    @Id
    @GeneratedValue
    private Long id;

    private Long codTipoSol;
    private String tipoSolicitud;
    private String tipoTramite;
    private String todosTramites;
    private String clase;
    
    @ManyToOne
    @JoinColumn(name = "id_plan_comercial")
    private PlanComercial planComercial;


}
