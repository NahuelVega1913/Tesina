package org.example.backendtesina.controllers;

import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.example.backendtesina.DTOs.Post.PostPayDTO;
import org.example.backendtesina.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    SaleService service;

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        return null;
    }
    @PostMapping(value = "payProduct")
    public ResponseEntity<?> pay(@RequestBody PostPayDTO entity) throws MPException, MPApiException {
        //int id =service.payMercadoPago(entity);
        String initPoint = service.payMercadoPago(entity);
        Map<String, String> response = new HashMap<>();
        response.put("init_point", initPoint);

        return ResponseEntity.ok(response);
    }
}
