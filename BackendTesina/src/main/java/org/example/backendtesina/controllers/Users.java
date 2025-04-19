package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.RegisterDto;
import org.example.backendtesina.entities.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class Users {



    @GetMapping(value = "getUser")
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("CARLOS");
    }

    @PutMapping( value = "getAll")
    public ResponseEntity<UserEntity> putUser(@RequestBody RegisterDto datos){
        return null;
    }
}
