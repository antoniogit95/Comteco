package com.example.api.ordenDia.cliente;

import com.example.api.Person.Person;
import com.example.api.ordenDia.direccion.Direccion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @JoinColumn(name = "id_person")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;
}
