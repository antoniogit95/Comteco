package com.example.api.Cesion;

import java.sql.Timestamp;
import com.example.api.User.User;
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
public class Cesion {
    
    @Id
    @GeneratedValue
    private Long id_secion;
    private Timestamp createdAt;
    private Timestamp finalyAt;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
