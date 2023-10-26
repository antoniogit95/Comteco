package com.example.api.Routes.Fdt;

import com.example.api.Routes.Odf.Odf;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa a una Fdts de las rutas
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fdt {
    
    /**
     * Identificador de Fds
     * Se generar de manera automatica
     */
    @Id
    private Long id_fdt;
    private String cod;
    private String tec;
    private String res;
    private String mc;
    private String mbc;
    private String mac;
    private String oc;

    /**
     * Relacion de muchoa a uno con la entidad ODF relacioando a su id
     */
    @ManyToOne
    @JoinColumn(name = "id_odf")
    private Odf odf;
}
