package org.example.backendtesina.controllers;

import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import org.example.backendtesina.DTOs.Post.PostPayDTO;
import org.example.backendtesina.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    SaleService service;

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        return null;
    }
    @GetMapping(value = "payProducto")
    public ResponseEntity<?> pay(@RequestBody PostPayDTO entity){
        //int id =service.payMercadoPago(entity);

        PreferenceBackUrlsRequest backUrls =
                PreferenceBackUrlsRequest.builder()
                        .success("https://www.seu-site/success")
                        .pending("https://www.seu-site/pending")
                        .failure("https://www.seu-site/failure")
                        .build();

        PreferenceRequest request = PreferenceRequest.builder().backUrls(backUrls).build();

        return null;
    }
}
