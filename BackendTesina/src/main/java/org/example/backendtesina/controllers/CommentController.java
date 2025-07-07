package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.Post.postComment;
import org.example.backendtesina.services.CommnetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("commnent")
public class CommentController {

    @Autowired
    CommnetService service;

    @GetMapping(value = "getCommentsbyId/{id}")
    public ResponseEntity<?> getComents(@PathVariable int id){
        List<postComment> lst = service.getAllComents(id);
        if(lst == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PostMapping(value = "PostComment")
    public ResponseEntity<?> PostComent(@RequestBody postComment commnet){
        postComment lst = service.postComment(commnet);
        if(lst == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lst);
    }




}
