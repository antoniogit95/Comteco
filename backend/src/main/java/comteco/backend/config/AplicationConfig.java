package comteco.backend.config;

import comteco.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
* Configuración de la aplicación que define beans relacionados con la autenticación y seguridad.
*/
@Configuration
@RequiredArgsConstructor
public class AplicationConfig {

    private final UserRepository userRepository;

    /**
    * Define un bean que proporciona un administrador de autenticación personalizado.
    *
    * @param configuration La configuración de autenticación.
    * @return Un administrador de autenticación que permite autenticar a los usuarios.
    * @throws Exception Si se produce un error al obtener el administrador de autenticación.
    */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
    * Define un bean que proporciona un proveedor de autenticación personalizado.
    *
    * @return Un proveedor de autenticación que se utiliza para autenticar a los usuarios.
    */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
    * Define un bean que proporciona un codificador de contraseñas (PasswordEncoder).
    *
    * @return Un codificador de contraseñas que se utiliza para codificar y verificar contraseñas de usuario.
        */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
    * Define un bean que proporciona un servicio de detalles de usuario (UserDetailsService).
    *
    * @return Un servicio que busca y recupera detalles de usuario por nombre de usuario.
    */
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
