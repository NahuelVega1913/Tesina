package org.example.backendtesina.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController("/turnos")
public class TurnoController {



    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.badRequest().build();
    }



    @PostMapping(value = "postTurno")
    public ResponseEntity<?> postTurno(){
        return ResponseEntity.badRequest().build();
    }
    @PutMapping(value = "putTurno")
    public ResponseEntity<?> putTurno(){
        return ResponseEntity.badRequest().build();
    }
}
