package org.example.backendtesina.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatinteligence")
public class ChatController {

    @PostMapping(value = "postmessage")
    public ResponseEntity<?> postMesagge(){
        return null;
    }
}
