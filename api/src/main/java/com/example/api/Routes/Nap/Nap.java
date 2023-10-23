package com.example.api.Routes.Nap;

import com.example.api.Routes.Fdt.Fdt;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
public class Nap {

    @Id
    @GeneratedValue
    private Long id_nap;
    private String cod;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_fdt")
    private Fdt fdt;
}
