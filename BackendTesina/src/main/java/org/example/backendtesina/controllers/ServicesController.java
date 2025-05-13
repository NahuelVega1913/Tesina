package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.Get.GetStatusService;
import org.example.backendtesina.DTOs.Post.PostInspection;
import org.example.backendtesina.entities.payment.CartEntity;
import org.example.backendtesina.entities.services.InspectionEntity;
import org.example.backendtesina.entities.services.ServiceEntity;
import org.example.backendtesina.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping(value = "postService")
    public ResponseEntity<?> post(@RequestBody PostInspection entity,@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7); // Elimina "Bearer "
        InspectionEntity cart = service.registerInspection(entity,token);
        if(cart == null) {
            return ResponseEntity.badRequest().body("Error");
        }
        return ResponseEntity.ok("");
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @GetMapping(value = "getStatusService")
    public ResponseEntity<?> getStatus(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7); // Elimina "Bearer "

        GetStatusService lst = service.getStatusService(token);
        if(lst == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lst);
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
