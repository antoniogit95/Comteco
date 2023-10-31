package com.example.api.Routes.Pos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PosResponseResume {
    Long id_pos;
    String nameODF;
    String codNAP;
}
