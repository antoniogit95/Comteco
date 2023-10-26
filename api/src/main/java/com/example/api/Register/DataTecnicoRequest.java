package com.example.api.Register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Continene todos lo parametros de entrada que son necesarios para hacer un registro de Datos tecnicos 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataTecnicoRequest {
    
    private Long id_person;
    private String num_producto;
    private String caja_nap;
    private String estado_odt;
    private String observaciones;
}
