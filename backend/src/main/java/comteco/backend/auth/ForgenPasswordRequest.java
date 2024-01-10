package comteco.backend.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForgenPasswordRequest {
    private String ci;
    private String item;
    private String email;
    private String password;
}   
