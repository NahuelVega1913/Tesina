package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.Post.PostEmployee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        return null;
    }
    @GetMapping(value = "getEmployee{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id){
        return null;
    }
    @PutMapping
    public ResponseEntity<?> modifyEmployee(PostEmployee employee){
        return null;
    }
}
