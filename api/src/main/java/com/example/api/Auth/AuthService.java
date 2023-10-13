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

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
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
                .person(person)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .person(person)
                .build();
    }

    private Timestamp getTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now);
    }
}
