package com.example.api.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.example.api.Person.Person;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(attributePaths = "person")
    Optional<User> findByUsername(String username);
    Optional<User> findByPerson(Person person);
}
