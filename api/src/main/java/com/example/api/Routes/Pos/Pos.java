package com.example.api.Routes.Pos;

import com.example.api.Routes.Nap.Nap;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa a las posiciones en las rutas
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pos {

    /**
     * Identificador de Posiciones de una nap
     * Se generarn de manera automatica
     */
    @Id
    @GeneratedValue
    private Long id_pos;
    private String cod;
    private boolean estado;

    /**
     * Relacion de Muchos a uno con la entidad NAP de referencia a su id
     */
    @ManyToOne
    @JoinColumn(name = "id_nap")
    private Nap nap;
}
