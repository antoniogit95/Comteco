package com.example.api.ordenDia.servicio;

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
public class Servicio {
    @Id
    @GeneratedValue
    private Long id;
    private Long cod;
    private String claseServicio;
    private String numeroServicio;
    private String componente;
    private String estado;
}
