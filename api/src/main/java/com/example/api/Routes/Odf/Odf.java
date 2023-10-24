package com.example.api.Routes.Odf;

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
public class Odf{
    
    @Id
    @GeneratedValue
    private Long id_odf;
    private String codigo;
    private String descripcion;
    private String nombre;
}
