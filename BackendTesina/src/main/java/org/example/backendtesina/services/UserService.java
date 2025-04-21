package org.example.backendtesina.services;

import org.apache.catalina.User;
import org.example.backendtesina.DTOs.RegisterDto;
import org.example.backendtesina.DTOs.changePasswordDTO;
import org.example.backendtesina.entities.UserEntity;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder encoder;


    public RegisterDto getUser(String mail){
        if(repository.findByEmail(mail).isPresent()){
            RegisterDto response = new RegisterDto();
            UserEntity entity = repository.findByEmail(mail).get();
            response.setAddress(entity.getAddress());
            response.setEmail(entity.getEmail());
            response.setLastname(entity.getLastname());
            response.setName(entity.getName());
            response.setPassword(entity.getPassword());
            response.setPhone(entity.getPhone());
            return response;
        }

        return null;
    }
    public RegisterDto UpdateUser(RegisterDto dto){
        if(repository.findByEmail(dto.getEmail()).isPresent()){
            RegisterDto response = new RegisterDto();
            UserEntity entity = repository.findByEmail(dto.getEmail()).get();
            entity.setAddress(dto.getAddress());
            entity.setName(dto.getName());
            entity.setLastname(dto.getLastname());
            entity.setPhone(dto.getPhone());
            repository.save(entity);
            response.setAddress(entity.getAddress());
            response.setEmail(entity.getEmail());
            response.setLastname(entity.getLastname());
            response.setName(entity.getName());
            response.setPassword(entity.getPassword());
            response.setPhone(entity.getPhone());
            return response;
        }
        return null;
    }
    public void  changePassword(changePasswordDTO changePassword){

    }

    public List<RegisterDto> getAllUsers(){
        return null;
    }
}
