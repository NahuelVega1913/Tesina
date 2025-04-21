package org.example.backendtesina.controllers;

import org.apache.catalina.User;
import org.example.backendtesina.DTOs.RegisterDto;
import org.example.backendtesina.entities.UserEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersController {

    @Autowired
    UserService service;
    @Autowired
    JwtService jwtService;


    @GetMapping(value = "getUser")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authHeader){
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = jwtService.getEmailFromToken(token);
            // hacé lo que necesites con el token o el email
            RegisterDto response = service.getUser(email);
            if(response == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(403).build();
    }



    @PutMapping( value = "putUser")
    public ResponseEntity<?> putUser(@RequestBody RegisterDto datos){
       RegisterDto response = service.UpdateUser(datos);
       if(response == null){
            return ResponseEntity.badRequest().build();
       }
       return ResponseEntity.ok(response);
    }

}
