package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Login.RegisterDto;
import org.example.backendtesina.DTOs.Login.changePasswordDTO;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
       List<RegisterDto> lstRegisters = new ArrayList<>();
       List<UserEntity> lstUsers = new ArrayList<>();
       lstUsers = repository.findAll();
       for (UserEntity u:lstUsers){
           RegisterDto dto = new RegisterDto();
           dto.setPhone(u.getPhone());
           dto.setEmail(u.getEmail());
           dto.setName(u.getName());
           dto.setLastname(u.getLastname());
           dto.setAddress(u.getAddress());
           lstRegisters.add(dto);
       }
       return lstRegisters;
    }
}
