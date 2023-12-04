package comteco.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import comteco.backend.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

/**
* Clase de configuración de seguridad que define la política de seguridad y la configuración de autenticación.
*/

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
            .csrf(csrf -> 
                csrf.disable())
            .authorizeHttpRequests(authReques ->
                authReques
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement(sessionManager ->
                sessionManager
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .cors(cors -> 
                cors.configurationSource(corsConfig.corsConfigurationSource()))
            .build();
    }
}
