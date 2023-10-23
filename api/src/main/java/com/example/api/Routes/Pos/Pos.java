package com.example.api.Routes.Pos;

import com.example.api.Routes.Nap.Nap;

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
public class Pos {

    @Id
    @GeneratedValue
    private Long id_pos;
    private String cod;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_nap")
    private Nap nap;
}
