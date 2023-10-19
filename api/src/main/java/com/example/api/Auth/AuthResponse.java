package com.example.api.Auth;

import com.example.api.Person.Person;
import com.example.api.User.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
    Person person;
    Role role;
    String message;
    int time;
}
