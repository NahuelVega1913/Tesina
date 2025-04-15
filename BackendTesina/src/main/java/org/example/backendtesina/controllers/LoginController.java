package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.LoginDto;
import org.example.backendtesina.DTOs.RegisterDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @PostMapping("/authenticate")
    public String login(@RequestBody LoginDto loginDto ) {

        return "Login successful";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterDto registerDto) {
        // Aquí puedes agregar la lógica para registrar al usuario
        return "User registered successfully";
    }
}
