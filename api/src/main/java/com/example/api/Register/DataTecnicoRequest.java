package com.example.api.Register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataTecnicoRequest {
    
    private Long id_person;
    private String num_producto;
    private String caja_nap;
    private String estadp_odt;
    private String obasrvaciones;
}
