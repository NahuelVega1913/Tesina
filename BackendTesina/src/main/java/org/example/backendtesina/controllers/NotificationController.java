package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.Get.GetNotification;
import org.example.backendtesina.entities.personal.NotificationEntity;
import org.example.backendtesina.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    NotificationService service;

    @GetMapping(value = "getAllNotifications")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7); // Elimina "Bearer "
        // Ahora sí, acá tenés tu token limpio
        List<GetNotification> notifications = service.getAll(token);
        if(notifications.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(notifications);
    }
    @PutMapping(value = "putNotification")
    public ResponseEntity<?> putNotification(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        List<NotificationEntity> notifications = service.readNotifications(token);
        if(notifications.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(notifications);
    }
//    @PostMapping(value = "createUser")
//    public ResponseEntity<?> createUserNotification(@RequestHeader("Authorization") String authorizationHeader){
//        String token = authorizationHeader.substring(7); // Elimina "Bearer "
//        NotificationEntity entity = service.createUserNotification(token);
//        if(entity == null){
//            return ResponseEntity.badRequest().build();
//        }else{
//            return ResponseEntity.ok("Notificacion creada");
//        }
//    }
//    @PostMapping(value = "buyProduct")
//    public ResponseEntity<?> buyProductNotification(@RequestHeader("Authorization") String authorizationHeader){
//        String token = authorizationHeader.substring(7); // Elimina "Bearer "
//        NotificationEntity entity = service.createUserNotification(token);
//        if(entity == null){
//            return ResponseEntity.badRequest().build();
//        }else{
//            return ResponseEntity.ok("Notificacion creada");
//        }
//    }
}
