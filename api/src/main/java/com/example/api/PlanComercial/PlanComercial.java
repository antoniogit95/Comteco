package com.example.api.PlanComercial;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlanComercial{

    @Id
    @GeneratedValue
    private Long id;
    private String planComercial;
    private String codLab;
    private String codigoPlanComercial;
    private String nuevoNombre;// (proporcionado Por GC)
    private String codigoAlternativa;// Alternativa (actual)
    private String tipoPlan;
    private String nuevaVelocidad;
    private String tipoEquipo;
    private String planCorto;
    private String nuevaTarifa;
    private String CorrespondeInternet;
    private String tarifa1;
    private String tarifa2;
    private String tarifa3;
    private String fechaCreacion;
    private String codigoHelpDesk;
    private String kbpsDownInternet;
    private String kbpsUpInternet;
    private String kbpsDown8To20;
    private String kbpsUp8To20;
    private String kbpsDown20To8;
    private String kbpsUp20To8;
    private String kbpsDownRedesSociales;
    private String kbpsUpRedesSociales;
    private String kbpsDown4K;
    private String kbpsUp4K;
    private String incrementoMbps;
    private String notas;
}
