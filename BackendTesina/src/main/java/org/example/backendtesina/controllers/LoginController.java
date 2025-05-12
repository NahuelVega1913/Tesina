package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.Login.AuthResponse;
import org.example.backendtesina.DTOs.Login.LoginDto;
import org.example.backendtesina.DTOs.Login.RegisterDto;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.repositories.UserRepository;
import org.example.backendtesina.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class LoginController {


    @Autowired
    LoginService service;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository repository;

    @PostMapping(value = "authenticate")
    public AuthResponse login(@RequestBody LoginDto loginDto ) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        UserDetails user= repository.findByEmail(loginDto.getEmail()).orElseThrow();
        UserEntity userEntity= repository.findByEmail(loginDto.getEmail()).orElseThrow();
        String Token = jwtService.getToken(user);
        return new AuthResponse(Token,userEntity.getName(),userEntity.getLastname(),userEntity.getRole().toString());
    }

    @PostMapping(value = "register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if(registerDto.getEmail() == null || registerDto.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }
        if(service.registerUser(registerDto) == null) {
            return ResponseEntity.badRequest().body("Usuario ya existente");
        }
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"Usuario creado correctamente\"}");
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @GetMapping(value = "getAllUsers")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        if(service.getAllUsers().size() == 0){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(service.getAllUsers());
        }
    }
//    @GetMapping(value = "getUser")
//    public ResponseEntity<UserEntity> getUser(){
//        return new UserEntity();
//    }
}
