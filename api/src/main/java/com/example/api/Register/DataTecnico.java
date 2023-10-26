package com.example.api.Register;

import java.sql.Timestamp;

import com.example.api.Person.Person;

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
 * Esta clase representa a un dato tecnico en el sistema
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DataTecnico {
    
    /**
     * Identificador único del usuario.
     * Se genera de manera automatica
     */
    @Id
    @GeneratedValue
    private Long id_reg_data_tec;
    private String num_producto;
    private String caja_nap;
    private String estadp_odt;
    private String obasrvaciones;
    private Timestamp created_at;
    private Timestamp update_at;

    /**
     * Relación de muchos a uno con la entidad 'Person'.
     */
    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;
}
