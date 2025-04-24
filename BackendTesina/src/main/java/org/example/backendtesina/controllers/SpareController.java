package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.GetProvidersDTO;
import org.example.backendtesina.DTOs.GetSpareDTO;
import org.example.backendtesina.DTOs.PostProviderDTO;
import org.example.backendtesina.DTOs.PostSpareDTO;
import org.example.backendtesina.services.ProviderService;
import org.example.backendtesina.services.SpareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spares")
public class SpareController {
    @Autowired
    SpareService service;


    @GetMapping(value ="getAll")
    public ResponseEntity<?> getProviders(){
        List<GetSpareDTO> lst = service.getAll();
        if(lst.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lst);
    }
    @GetMapping(value ="getSpare/{id}")
    public ResponseEntity<?> getProvider(@PathVariable int id){
        PostSpareDTO lst = service.getSpare(id);
        if(lst == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PostMapping(value ="postSpare")
    public ResponseEntity<?> postProviders(@RequestBody PostSpareDTO dto){
        PostSpareDTO lst = service.postSpare(dto);
        if(lst == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PutMapping(value ="putSpare")
    public ResponseEntity<?> putProviders(@RequestBody PostSpareDTO dto){
        PostSpareDTO lst = service.putSpare(dto);
        if(lst == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lst);
    }
}
