package com.example.api.Auth;

import com.example.api.Jwt.JwtService;
import com.example.api.Person.Person;
import com.example.api.Person.PersonRepository;
import com.example.api.User.Role;
import com.example.api.User.User;
import com.example.api.User.UserRepository;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;

    public ResponseEntity<AuthResponse> login(LoginRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));    
            UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            Person person = ((User) user).getPerson();
            Role role = ((User) user).getRole();
            boolean status = ((User) user).isStatus();
            if(!status){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthResponse.builder()
                .message("No tienes permisos de autenticación.")
                .build());
            }
            String token = jwtService.getToken(user);
            return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .person(person)
                .role(role)
                .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthResponse.builder()
            .message("Nombre de usuario o contraseña incorrectos.")
            .build());
        }        
    }

    public  AuthResponse register(RegisterRequest request){
        Person person = Person.builder()
                .celula_identidad(request.getCelula_identidad())
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .item(request.getItem())
                .fecha_nacimiento(request.getFecha_nacimiento())
                .email(request.getEmail())
                .telefono(request.getTelefono())
                .created_at(getTimestamp())
                .update_at(getTimestamp())
                .build();
        personRepository.save(person);
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(false)
                .role(Role.ADMIN)
                .created_at(getTimestamp())
                .update_at(getTimestamp())
                .person(person)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .person(person)
                .role(user.getRole())
                .build();
    }

    private Timestamp getTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now);
    }
}
