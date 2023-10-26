package com.example.api.Routes.Odf;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa a una Odf en el sistema
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Odf{
    
    /**
     * Identificador único del usuario.
     * Se genera de manera automatica
     */
    @Id
    @GeneratedValue
    private Long id_odf;
    private String codigo;
    private String descripcion;
    private String nombre;
}
