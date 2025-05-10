package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.Get.GetEmployee;
import org.example.backendtesina.DTOs.Get.GetProvidersDTO;
import org.example.backendtesina.DTOs.Post.PostEmployee;
import org.example.backendtesina.DTOs.Post.PostProviderDTO;
import org.example.backendtesina.entities.EmployeeEntity;
import org.example.backendtesina.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        List<GetEmployee> lst = service.getAll();
        if(lst.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @GetMapping(value = "getEmployee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id){
        PostEmployee lst = service.getEmployee(id);
        if(lst == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @PutMapping(value = "putEmployee")
    public ResponseEntity<?> modifyEmployee(@RequestBody PostEmployee dto){
        EmployeeEntity lst = service.ModifyEmploye(dto);
        if(lst == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @PostMapping(value ="postEmployee")
    public ResponseEntity<?> postProviders(@RequestBody PostEmployee dto){
        EmployeeEntity lst = service.createEmploye(dto);
        if(lst == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lst);
    }
}
