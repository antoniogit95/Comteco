package com.example.api.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.api.Person.Person;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * Clase que representa a un usuario en el sistema de comteco.
 * Esta clase es una entidad de la base de datos y también implementa la interfaz UserDetails de Spring Security.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {
      /**
     * Identificador único del usuario.
     * Se genera de manera automatica
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * Nombre de usuario del usuario. Debe ser único en el sistema.
     */
    @Column(nullable = false)
    private String username; 
    private String password; //contraseña encriptada
    private boolean status; //Estado del usuario si tiene o no permisos de autentificacion
    private Timestamp created_at; //fecha de creacion
    private Timestamp update_at; //fecha de actualizacion
    /**
     * Rol del usuario, representado como un valor Enum.
     * los roles pueden ser: ADMIN, SOPORTE y SUPERVISOR.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Relación uno a uno con la entidad Person. Asocia al usuario con una persona.
     */
    @OneToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private Person person;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
