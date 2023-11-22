package com.example.api.ordenDia.cliente;

import com.example.api.Person.Person;
import com.example.api.ordenDia.direccion.Direccion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {
    
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @Column(name = "id_person")
    private Person person;

    @ManyToOne
    @Column(name = "id_direccion")
    private Direccion direccion;
}
