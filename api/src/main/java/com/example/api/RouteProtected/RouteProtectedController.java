package com.example.api.RouteProtected;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RouteProtectedController {

    @PostMapping(value = "demo")
    public String welcome(){
        return "Welcome form secure endpoint";
    }
}
