package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.LoginDto;
import org.example.backendtesina.entities.UserEntity;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserRepository repository;

    public int validateUser(LoginDto login) {
        UserEntity user = repository.findById(login.getEmail()).orElse(null);
        if (user != null) {
            if (user.getPassword().equals(login.getPassword())) {
                return 1; // Usuario y contraseña válidos
            } else {
                return 0; // Contraseña incorrecta
            }
        }
        return -1; // Usuario no encontrado
    }
    public int registerUser(UserEntity user) {
        if (repository.existsById(user.getEmail())) {
            return 0; // El usuario ya existe
        } else {
            repository.save(user);
            return 1; // Usuario registrado con éxito
        }
    }
}
