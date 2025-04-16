package org.example.backendtesina.services;

import org.apache.catalina.User;
import org.example.backendtesina.DTOs.LoginDto;
import org.example.backendtesina.DTOs.RegisterDto;
import org.example.backendtesina.entities.UserEntity;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int registerUser(RegisterDto register) {
        UserEntity user = new UserEntity();
        user.setEmail(register.getEmail());
        user.setPassword(register.getPassword());
        user.setName(register.getName());
        user.setLastname(register.getLastname());
        user.setPhone(register.getPhone());
        user.setAddress(register.getAddress());

        if (repository.existsById(user.getEmail())) {
            return 0; // El usuario ya existe
        } else {
            repository.save(user);
            if(repository.existsById(register.getEmail()) ){
                return 1;// Usuario registrado con éxito
            }
        }
        return 0;
    }

    public List<UserEntity> getAllUsers(){
        return repository.findAll();
    }
}
