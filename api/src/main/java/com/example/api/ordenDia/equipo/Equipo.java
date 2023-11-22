package com.example.api.ordenDia.equipo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Equipo {
    
    @Id
    @GeneratedValue
    Long id;

    private String macAddress;
    private String tipo;
    private String modelo;
    private String fabricante;
    private String wifi;
    private String frecOperacion;
    private String salidaTv;
    private String caracteristicas;
    private String terminacionSerie;
}
