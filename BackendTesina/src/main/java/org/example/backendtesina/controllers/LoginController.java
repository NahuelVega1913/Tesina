package org.example.backendtesina.controllers;

import io.swagger.v3.core.util.Json;
import org.example.backendtesina.DTOs.AuthResponse;
import org.example.backendtesina.DTOs.LoginDto;
import org.example.backendtesina.DTOs.RegisterDto;
import org.example.backendtesina.entities.UserEntity;
import org.example.backendtesina.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class LoginController {


    @Autowired
    LoginService service;

    @PostMapping(value = "authenticate")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto ) {
        AuthResponse response = service.validateUser(loginDto);
        if(response.getToken() != null){
            return ResponseEntity.ok(service.validateUser(loginDto) );
        }
            return ResponseEntity.badRequest().build();

    }

    @PostMapping(value = "register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if(registerDto.getEmail() == null || registerDto.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }
        if(service.registerUser(registerDto) != null) {
            return ResponseEntity.badRequest().body("Usuario ya existente");
        }
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"Usuario creado correctamente\"}");
    }
    @GetMapping(value = "getAllUsers")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        if(service.getAllUsers().size() == 0){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(service.getAllUsers());
        }
    }
}
