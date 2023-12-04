package com.example.api.Config;

import com.example.api.Jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
* Clase de configuración de seguridad que define la política de seguridad y la configuración de autenticación.
*/
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CorsConfig corsConfig;

     /**
     * Define una cadena de filtros de seguridad que gestiona la autenticación y autorización de las solicitudes.
     *
     * @param http La configuración de seguridad HTTP.
     * @return Una cadena de filtros de seguridad configurada para la aplicación.
     * @throws Exception Si se produce un error al configurar la seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
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
                .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    
}

/**.authorizeHttpRequests(authReques ->
                        authReques
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated()
                ) 
.authorizeRequests( (authRequest) -> 
                        authRequest
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .mvcMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated();
                )*/