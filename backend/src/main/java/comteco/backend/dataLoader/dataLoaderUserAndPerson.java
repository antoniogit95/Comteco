package comteco.backend.dataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import comteco.backend.auth.AuthService;
import comteco.backend.person.Person;
import comteco.backend.person.PersonRepository;
import comteco.backend.user.Role;
import comteco.backend.user.User;
import comteco.backend.user.UserRepository;

@Component
public class dataLoaderUserAndPerson implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Verificar si ya existe un usuario administrador
        if (userRepository.findByUsername("admin").isEmpty()) {
            // Crear una persona asociada al administrador
            Person adminPerson = Person.builder()
                .cedulaIdentidad("0000")
                .nombre("admin")
                .apellidos("root")
                .item("0000")
                .fecha_nacimiento(null)
                .email("admin")
                .telefono("0000")
                .status(true)
                .createdAt(authService.getTimestamp())
                .updateAt(authService.getTimestamp())
                .build();
        personRepository.save(adminPerson);
        User user = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .status(true)
                .role(Role.ADMIN)
                .created_at(authService.getTimestamp())
                .update_at(authService.getTimestamp())
                .person(adminPerson)
                .build();
        userRepository.save(user);
        }
    }
}