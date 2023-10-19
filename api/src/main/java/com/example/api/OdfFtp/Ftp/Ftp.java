package com.example.api.OdfFtp.Ftp;

import com.example.api.OdfFtp.Odf.Odf;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ftp {
    
    @Id
    private Long id_fdt;
    private String cod;
    private String tec;
    private String res;
    private String mc;
    private String mbc;
    private String mac;
    private String oc;

    @ManyToOne
    @JoinColumn(name = "id_odf")
    private Odf odf;
}
