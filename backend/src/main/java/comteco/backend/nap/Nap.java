package comteco.backend.nap;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private Long id;

    private String odf;
    private String fdt;
    private String nap;
    private String cod;
    private String ubicacion;
    private String estado;
    private String descripcion;
}
