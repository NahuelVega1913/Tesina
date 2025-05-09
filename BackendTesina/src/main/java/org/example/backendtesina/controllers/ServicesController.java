package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.Get.GetSaleDTO;
import org.example.backendtesina.entities.CartEntity;
import org.example.backendtesina.services.SaleService;
import org.example.backendtesina.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController {
    @Autowired
    ServiceService service;
    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
//        List<GetSaleDTO> lst = service.getAllServices();
//        if(lst.isEmpty()){
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(lst);
        return null;
    }
    @PostMapping(value = "postService")
    public ResponseEntity<?> post(){
//        String token = authorizationHeader.substring(7); // Elimina "Bearer "
//        CartEntity cart = service.postCart(token, idSpare);
//        if(cart == null){
//            return ResponseEntity.badRequest().body("Error");
//        }
        return ResponseEntity.ok("");
    }

    @PutMapping (value = "modifyService")
    public ResponseEntity<?> ModifyCart(){
//        String token = authorizationHeader.substring(7); // Elimina "Bearer "
//        CartEntity cart = service.putCart(token, idSpare);
//        if(cart == null){
//            return ResponseEntity.badRequest().body("Error");
//        }
       return ResponseEntity.ok("");
    }
}
