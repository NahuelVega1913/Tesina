package org.example.backendtesina.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@CrossOrigin("*")
public class Ping {

    @GetMapping(value = "/ping")
    public ResponseEntity<String> pingpong(){
        return ResponseEntity.ok("PONG");
    }
}
