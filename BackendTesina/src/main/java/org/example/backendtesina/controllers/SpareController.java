package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.Get.GetSpareDTO;
import org.example.backendtesina.DTOs.Post.PostSpareDTO;
import org.example.backendtesina.services.SpareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/spares")
public class SpareController {
    @Autowired
    SpareService service;


    @GetMapping(value ="getAll")
    public ResponseEntity<?> getSpare(){
        List<GetSpareDTO> lst = service.getAll();
        if(lst.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lst);
    }
    @GetMapping(value ="getSpare/{id}")
    public ResponseEntity<?> getSpareById(@PathVariable int id){
        PostSpareDTO lst = service.getSpare(id);
        if(lst == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @PostMapping(value ="postSpare")
    public ResponseEntity<?> postSpare(
            @RequestParam String name,
            @RequestParam int idProvider,
            @RequestParam double price,
            @RequestParam int discaunt,
            @RequestParam int stock,
            @RequestParam String brand,
            @RequestParam Double stars,
            @RequestParam String category,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile image1,
            @RequestParam(required = false) MultipartFile image2,
            @RequestParam(required = false) MultipartFile image3,
            @RequestParam(required = false) MultipartFile image4,
            @RequestParam(required = false) MultipartFile image5) throws IOException {

           PostSpareDTO carlos = service.postSpare(
                    name,idProvider ,price, discaunt, stock, brand,stars, category,
                    description, image1, image2, image3, image4, image5
            );
            if(carlos == null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar repuesto");
            }
            return ResponseEntity.ok(carlos);

    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @PutMapping(value ="putSpare")
    public ResponseEntity<PostSpareDTO> updateSpare(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam int providerId,
            @RequestParam double price,
            @RequestParam int discaunt,
            @RequestParam int stock,
            @RequestParam String brand,
            @RequestParam Double stars,
            @RequestParam String category,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile image1,
            @RequestParam(required = false) MultipartFile image2,
            @RequestParam(required = false) MultipartFile image3,
            @RequestParam(required = false) MultipartFile image4,
            @RequestParam(required = false) MultipartFile image5
    ) {
        try {
            PostSpareDTO updatedSpare = service.putSpare(id, name, providerId, price, discaunt, stock, brand, stars,
                    category, description, image1, image2, image3, image4, image5);
            return ResponseEntity.ok(updatedSpare);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }}
//    @PutMapping(value ="putSpare")
//    public ResponseEntity<?> putSpare(@RequestBody PostSpareDTO dto){
//        PostSpareDTO lst = service.putSpare(dto);
//        if(lst == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(lst);
//    }

