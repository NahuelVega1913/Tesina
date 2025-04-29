package org.example.backendtesina.controllers;

import org.apache.coyote.Response;
import org.example.backendtesina.DTOs.Get.GetCartDTO;
import org.example.backendtesina.entities.CartEntity;
import org.example.backendtesina.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService service;


    @GetMapping(value = "get")
    public ResponseEntity<?> getCart(@RequestHeader("Authorization") String authorizationHeader){
            String token = authorizationHeader.substring(7); // Elimina "Bearer "
            // Ahora sí, acá tenés tu token limpio
            System.out.println("Token extraído: " + token);
            GetCartDTO cart = service.getCart(token);

        if(cart == null){
            return ResponseEntity.badRequest().body("Error");
        }
        return ResponseEntity.ok(cart);
    }

    @PostMapping(value = "addProduct/{idSpare}")
    public ResponseEntity<?> addProductToCart(@PathVariable int idSpare,@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7); // Elimina "Bearer "
        CartEntity cart = service.postCart(token, idSpare);
        if(cart == null){
            return ResponseEntity.badRequest().body("Error");
        }
        return ResponseEntity.ok(cart);
    }

    @PutMapping (value = "putCart/{idSpare}")
    public ResponseEntity<?> ModifyCart(@PathVariable int idSpare,@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7); // Elimina "Bearer "
        CartEntity cart = service.putCart(token, idSpare);
        if(cart == null){
            return ResponseEntity.badRequest().body("Error");
        }
        return ResponseEntity.ok(cart);
    }
}
