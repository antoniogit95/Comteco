package com.example.api.ordenDia.equipos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Equipo {
    
    @Id
    @GeneratedValue
    Long id;
}
