package org.example.backendtesina.controllers;


import org.example.backendtesina.DTOs.Get.GetEmployee;
import org.example.backendtesina.services.ChatService2;
import org.example.backendtesina.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chatinteligence")
public class ChatController {

    @Autowired
    ChatService2 service;

    @PostMapping(value = "postmessage")
    public ResponseEntity<?> postMesagge(@RequestBody String message){
        String mensaje = service.enviarMensaje(message);
        if(mensaje == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(mensaje);
    }
}
