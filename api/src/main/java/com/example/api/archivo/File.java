package com.example.api.archivo;


import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class File {
    @Id
    @GeneratedValue
    Long ID;
    Date FECHA_CREACION;
    Integer COD_TIPO_SOL;
    String TIPO_SOLICITUD;
    Integer COD_TIPO;
    String TIPO_TRABAJO;
    Integer COD_PLAN_COMERCIAL;
    String PLAN_COMERCIAL;
    String COMPONENTE;
    String CLASE_SERVICIO;
    String AREA_SERVICIO;
    String UBICACION;
    String PRODUCTO;
    String ORDEN;
    String DESC_SERVICIO;
    String NUMERO_SERVICIO;
    String ESTADO_COMPONENTE;
    String NAP;
    Integer POSICION;
    String DESCRIPCION;
    String CLIENTE;
    String DIRECCION;
    String COD_ESTADO_OT;
    String UNIDAD_OPERATIVA;

}
