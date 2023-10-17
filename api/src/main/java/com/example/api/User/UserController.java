package com.example.api.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    
    UserRepository userRepository;

    /**@PutMapping("/active/{idPersona}")
    public ResponseEntity<String> activarUsuarioPorIdPersona(@PathVariable Long id_person) {
    System.out.println("-----------------------------------------\n"+
    "logrando entrar a obtener datos del usuaro a travez de la persona\n"+
    "-------------------------------------------------");
    
    User user = userRepository.findByPersonIdPerson(id_person);
    if (user != null) {
        user.setStatus(true);
        userRepository.save(user);
        return ResponseEntity.ok("Usuario activado exitosamente");
    } else {
        return ResponseEntity.notFound().build();
    }
}*/
}
