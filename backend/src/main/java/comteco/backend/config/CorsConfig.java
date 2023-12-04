package comteco.backend.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Clase de configuración que define la política de manejo de solicitudes Cross-Origin Resource Sharing (CORS).
 */
@Configuration
public class CorsConfig {
    
    /**
     * Define una fuente de configuración de CORS que permite las solicitudes desde cualquier origen
     * y especifica los métodos y encabezados permitidos.
     *
     * @return Una fuente de configuración de CORS personalizada.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permite solicitudes desde cualquier origen (podría personalizarse según los requisitos de seguridad).
        configuration.addAllowedOrigin("*"); 
        
        // Especifica los métodos HTTP permitidos.
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Especifica los encabezados permitidos.
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        
        // Crea una fuente de configuración de CORS basada en URL que aplica la configuración a todas las rutas.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}