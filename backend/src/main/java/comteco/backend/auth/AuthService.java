package comteco.backend.auth;

import comteco.backend.sesion.SesionService;
import comteco.backend.jwt.JwtService;
import comteco.backend.person.Person;
import comteco.backend.person.PersonRepository;
import comteco.backend.user.User;
import comteco.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona la autenticación de usuarios y operaciones relacionadas.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;
    private final SesionService cesionService;

     /**
     * Inicia sesión de un usuario.
     *
     * @param request La solicitud de inicio de sesión que incluye las credenciales del usuario.
     * @return Una respuesta que contiene la información de autenticación del usuario si el inicio de sesión es exitoso.
     */
    public ResponseEntity<AuthResponse> login(LoginRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));    
            UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            boolean status = ((User) user).isStatus();
            if(!status){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthResponse.builder()
                .message("No tienes permisos de autenticación.")
                .build());
            }
            String token = jwtService.getToken(user);
            cesionService.saveCesion((User) user);
            return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .message("Token con exito")
                .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AuthResponse.builder()
            .message("Nombre de usuario o contraseña incorrectos.")
            .build());
        }        
    }

     /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request La solicitud de registro que incluye los datos del nuevo usuario 
     * dentro la clase RegisterRequest.
     * @return Una respuesta que contiene la información de autenticación del usuario 
     * si el registro es exitoso con los datos que estan dentro la clase AuthResponse.
     */
    public  AuthResponse register(RegisterRequest request){
        Person person = Person.builder()
                .cedulaIdentidad(request.getCelula_identidad())
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .item(request.getItem())
                .fecha_nacimiento(request.getFecha_nacimiento())
                .email(request.getEmail())
                .telefono(request.getTelefono())
                .status(false)
                .createdAt(getTimestamp())
                .updateAt(getTimestamp())
                .build();
        personRepository.save(person);
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(false)
                .role(request.getRole())
                .created_at(getTimestamp())
                .update_at(getTimestamp())
                .person(person)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(null)
                .message("Usuario creado Satisfactoriamente")
                .build();
    }

    public Timestamp getTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now);
    }

    public AuthResponse refreshToken(String username) {
        try {
            UserDetails user = userRepository.findByUsername(username).orElseThrow();
            String token = jwtService.getToken(user);
            System.out.println("------------------------ENTRANDO A ACTUALIZAR LA SESION ...: ");
            cesionService.updateCesion((User) user);
            return AuthResponse.builder()
                .token(token)
                .message("refres token, con exito.")
                .build();
        } catch (Exception e) {
            return AuthResponse.builder()
                .message("Error:" +e)
                .build();
        }
    }

    /**
     * 
     * @param username para saber si existe o no
     * @return un obgeto de tipo booleanno
     */
    public Boolean existUsernme(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @param ci para preguntar si existe o no en la base de datos
     * @return un objeto de tipo boolenano.
     */
    public Boolean existCi(String ci) {
        Optional<Person> pOptional = personRepository.findByCedulaIdentidad(ci);
        if (pOptional.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param itempara preguntar si existe o no en la base de datos
     * @return un objeto de tipo boolenano.
     */
    public Boolean existItem(String item) {
        Optional<Person> pOptional = personRepository.findByItem(item);
        if (pOptional.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Preguntar si el usuario ingresado si existe o no
     * @param request donde estan todos los datos unicos de un usuario como ser: email, ci, item.
     * @return un String especificando que error existe o no.
     */
    public ForgenPasswordRequest existDataByForgenPassword(ForgenPasswordRequest request) {
        Optional<User> userOptionar = userRepository.findByUsername(request.getEmail());
        if(userOptionar.isPresent()){
            Person person = userOptionar.get().getPerson();
            if(person.getItem().equals(request.getItem())){
                request.setMessage("SIN ERROR");
            }else{
                request.setMessage("Las credenciales no corresponden al Email");
            }
        }else{
            request.setMessage("El Email ingresado no esta registrado");
        }
        return request;
    }

    /**
     * Gurdar la nueva contraseña
     * @param request donde estan todos los datos unicos de un usuario como ser: email, ci, item y la contraseña
     * @return un String especificando que error existe o no.
     */
    public ResponseEntity<ForgenPasswordRequest> saveNewPasswordByEmail(ForgenPasswordRequest request) {
        Optional<User> userOptionar = userRepository.findByUsername(request.getEmail());
        if(userOptionar.isPresent()){
            User user = userOptionar.get();
            Person person = user.getPerson();
            if(person.getItem().equals(request.getItem())){
                request.setMessage("SIN ERROR");
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                userRepository.save(user);
                return new ResponseEntity<>(request, HttpStatus.OK); 
            }else{
                request.setMessage("Las credenciales no corresponden al Email");
                return new ResponseEntity<>(request, HttpStatus.NOT_FOUND);
            }
        }else{
            request.setMessage("El Email ingresado no esta registrado");
            return new ResponseEntity<>(request, HttpStatus.NOT_FOUND);
        }
    }
}
