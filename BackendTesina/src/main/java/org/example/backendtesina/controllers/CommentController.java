package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.Get.GetCartDTO;
import org.example.backendtesina.DTOs.Post.postComment;
import org.example.backendtesina.services.CommnetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("commnent")
public class CommentController {

    @Autowired
    CommnetService service;
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN','USER')")
    @GetMapping(value = "getcommentsbyId/{id}")
        public ResponseEntity<?> getComents(@PathVariable int id){
        List<postComment> lst = service.getAllComents(id);
        if(lst == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN','USER')")
    @PostMapping(value = "postcomment" )
    public ResponseEntity<?> PostComent(@RequestBody postComment commnet,@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7); // Elimina "Bearer "
        // Ahora sí, acá tenés tu token limpio

        postComment lst = service.postComment(commnet, token );
        if(lst == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lst);
    }




}
