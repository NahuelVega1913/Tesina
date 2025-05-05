package org.example.backendtesina.controllers;

import org.apache.coyote.Response;
import org.example.backendtesina.DTOs.Get.GetCartDTO;
import org.example.backendtesina.entities.NotificationEntity;
import org.example.backendtesina.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    NotificationService service;

    @GetMapping(value = "getAllNotifications")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String authorizationHeader){
        return null;
    }
    @PutMapping(value = "putNotification")
    public ResponseEntity<?> putNotification(@RequestHeader("Authorization") String authorizationHeader){
        return null;
    }
    @PostMapping(value = "createUser")
    public ResponseEntity<?> createUserNotification(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7); // Elimina "Bearer "
        NotificationEntity entity = service.createUserNotification(token);
        if(entity == null){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok("Notificacion creada");
        }
    }
    @PostMapping(value = "buyProduct")
    public ResponseEntity<?> buyProductNotification(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7); // Elimina "Bearer "
        NotificationEntity entity = service.createUserNotification(token);
        if(entity == null){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok("Notificacion creada");
        }
    }
}
