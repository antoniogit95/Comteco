package com.example.api.Cesion;

import java.sql.Timestamp;
import com.example.api.User.User;
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
 * Esta clase representa una cesion en el sistema
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cesion {
    
    /**
    * Identificador único de la cesion.
    * Se genera de manera automatica
    */
    @Id
    @GeneratedValue
    private Long id_secion;
    private Timestamp createdAt; //fecha y hora de inicio de secion
    private Timestamp finalyAt; //fecha y hora de finalizar la cesion

    /**
     * Relación de muchos a uno con la entidad User
     */
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
