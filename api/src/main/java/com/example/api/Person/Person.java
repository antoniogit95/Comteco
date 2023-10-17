package com.example.api.Person;

import java.sql.Date;
import java.sql.Timestamp;

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
public class Person {

    @Id
    @GeneratedValue
    private Long id_person;
    private String celula_identidad;
    private String nombre;
    private String apellidos;
    private String item;
    private Date fecha_nacimiento;
    private String email;
    private String telefono;
    private Timestamp created_at;
    private Timestamp update_at;

}