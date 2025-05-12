package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Login.AuthResponse;
import org.example.backendtesina.DTOs.Login.RegisterDto;
import org.example.backendtesina.entities.enums.RoleEntity;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    UserRepository repository;
    @Autowired
    JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    NotificationService notificationService;

//    public AuthResponse validateUser(LoginDto login) {
//        UserEntity user = repository.findById(login.getEmail()).orElse(null);
//        if (user != null) {
//            if (user.getPassword().equals(login.getPassword())) {
//                return null ; // Usuario y contraseña válidos
//            } else {
//                return null; // Contraseña incorrecta
//            }
//        }
//        return null; // Usuario no encontrado
//    }
    public AuthResponse  registerUser(RegisterDto register) {
        if (repository.findByEmail(register.getEmail()).isPresent()) {
            return null; // El usuario ya existe
        }
        UserEntity user = new UserEntity();
        user.setEmail(register.getEmail());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setName(register.getName());
        user.setLastname(register.getLastname());
        user.setPhone(register.getPhone());
        user.setAddress(register.getAddress());
        user.setRole(RoleEntity.USER);
        repository.save(user);
        if(repository.existsById(register.getEmail()) ){
            notificationService.createUserNotification(user);
            return new AuthResponse(jwtService.getToken(user));// Usuario registrado con éxito
        }
        return null;
    }

    public List<UserEntity> getAllUsers(){
        return repository.findAll();
    }
}
