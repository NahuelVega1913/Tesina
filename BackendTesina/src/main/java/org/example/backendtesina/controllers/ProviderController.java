package org.example.backendtesina.controllers;

import org.apache.coyote.Response;
import org.example.backendtesina.DTOs.GetProvidersDTO;
import org.example.backendtesina.services.ProviderService;
import org.example.backendtesina.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    ProviderService service;

    @GetMapping(value ="getAll")
    public ResponseEntity<?> getProviders(){
        List<GetProvidersDTO> lst = service.getAll();
        if(lst.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lst);
    }
    @GetMapping(value ="getAll{id}")
    public ResponseEntity<?> getProvider(@PathVariable int id){
        return ResponseEntity.ok("");
    }
    @PostMapping(value ="postProvider")
    public ResponseEntity<?> postProviders(){
        return ResponseEntity.ok("");
    }
    @PutMapping(value ="putProvider")
    public ResponseEntity<?> putProviders(){
        return ResponseEntity.ok("");
    }
}
