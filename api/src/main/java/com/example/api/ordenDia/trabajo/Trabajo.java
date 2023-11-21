package com.example.api.ordenDia.trabajo;

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
public class Trabajo {

    @Id
    @GeneratedValue
    private Long id;
    private Long codTipo;
    private String tipoTrabajo;
    private String resumido;
}
