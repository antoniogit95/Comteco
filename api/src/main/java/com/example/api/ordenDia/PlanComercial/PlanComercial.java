package com.example.api.ordenDia.PlanComercial;

import com.example.api.ordenDia.equipos.Equipo;

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
public class PlanComercial{

    @Id
    @GeneratedValue
    private Long id;
    private String codLab;
    private String nuevoNombre;// (proporcionado Por GC)
    private String tipoPlan;
    private String nuevaVelocidad;
    private String planCorto;


    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo tipoEquipo;
}
