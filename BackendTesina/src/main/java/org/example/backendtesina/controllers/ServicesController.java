package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.Get.*;
import org.example.backendtesina.DTOs.Post.PostInspection;
import org.example.backendtesina.DTOs.Post.PostRepair;
import org.example.backendtesina.entities.payment.CartEntity;
import org.example.backendtesina.entities.services.CustomizationEntity;
import org.example.backendtesina.entities.services.InspectionEntity;
import org.example.backendtesina.entities.services.RepairEntity;
import org.example.backendtesina.entities.services.ServiceEntity;
import org.example.backendtesina.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController {
    @Autowired
    ServiceService service;
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        List<GetServices> lst = service.getServices();
       if(lst.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @GetMapping(value = "getService/{id}")
    public ResponseEntity<?> getSerive(@PathVariable int id){
        GetServices lst = service.getService(id);
        if(lst == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @PostMapping(value = "retireCar/{id}")
    public ResponseEntity<?> retireCar(@PathVariable int id){
        ServiceEntity lst = service.retireCard(id);
        if(lst == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lst);
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
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping(value = "postRepair")
    public ResponseEntity<?> postRepair(@RequestBody PostInspection entity, @RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7); // Elimina "Bearer "
        Object cart = service.registerRepair(entity,token);
        if(cart == null) {
            return ResponseEntity.badRequest().body("Error");
        }
        return ResponseEntity.ok("");
    }
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping(value = "postCustomization")
    public ResponseEntity<?> postCustomization(@RequestBody PostInspection entity,@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7); // Elimina "Bearer "
        CustomizationEntity cart = service.registerCustomization(entity,token);
        if(cart == null) {
            return ResponseEntity.badRequest().body("Error");
        }
        return ResponseEntity.ok("");
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @PostMapping(value ="registerEntry/{id}")
    public ResponseEntity<?> registerEntry(@PathVariable int id){
        ServiceEntity cart = service.registerEntry(id);
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
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @PutMapping(value = "putEmployees/{id}/{idEmployee}")
    public ResponseEntity<?> putEmpleados(@PathVariable int id,@PathVariable int idEmployee){

        ServiceEntity lst = service.addEmployes(id,idEmployee);
        if(lst == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @PostMapping(value = "finishInspection")
    public ResponseEntity<?> finishInspection(@RequestBody GetInspection inspection){

        InspectionEntity lst = service.FinishInspection(inspection);
        if(lst == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @PostMapping(value = "finishCustomization")
    public ResponseEntity<?> finishCustomization(@RequestBody GetCustomization inspection){

        CustomizationEntity lst = service.FinishCustomization(inspection);
        if(lst == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @PostMapping(value = "finishRepair")
    public ResponseEntity<?> finishRepair(@RequestBody GetRepair inspection){

        RepairEntity lst = service.FinishRepair(inspection);
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
