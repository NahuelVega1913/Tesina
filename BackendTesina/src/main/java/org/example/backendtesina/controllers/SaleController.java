package org.example.backendtesina.controllers;

import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.example.backendtesina.DTOs.Get.GetSaleDTO;
import org.example.backendtesina.DTOs.Post.PostPayDTO;
import org.example.backendtesina.entities.payment.SaleEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    SaleService service;
    @Autowired
    JwtService jwtService;
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
       List<GetSaleDTO> lst = service.getAllSales();
       if(lst.isEmpty()){
           return ResponseEntity.badRequest().build();
       }
       return ResponseEntity.ok(lst);
    }
    @PostMapping(value = "payProduct")
    public ResponseEntity<?> pay(@RequestBody PostPayDTO entity,@RequestHeader("Authorization") String authorizationHeader) throws MPException, MPApiException {
        String token = authorizationHeader.substring(7);
        //int id =service.payMercadoPago(entity);
        String initPoint = service.payMercadoPago(token,entity);
        Map<String, String> response = new HashMap<>();
        response.put("init_point", initPoint);

        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "webhook")
    public ResponseEntity<?> confirmPay(@RequestBody String s){
        String response = this.service.confirmPayment(s);
        if(response != null){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }

    }
    @PostMapping(value = "payProducts")
    public ResponseEntity<?> payCart(@RequestBody List<PostPayDTO> lst,@RequestHeader("Authorization") String authorizationHeader) throws MPException,MPApiException {
        String token = authorizationHeader.substring(7);
        String initPoint = service.payProductos(token,lst);
        if (initPoint == null) {
            return ResponseEntity.badRequest().build();
        }
        Map<String, String> response = new HashMap<>();
        response.put("init_point", initPoint);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @GetMapping(value = "getDetails/{id}")
    public ResponseEntity<?> getVentas(@PathVariable int id){
        GetSaleDTO lst = service.getDetails(id);
        if(lst == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lst);
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @PostMapping(value = "payCash")
    public ResponseEntity<?> payCash(@RequestBody PostPayDTO entity, @RequestParam String email) {

            SaleEntity s = service.payCash(email, entity);
            if(s == null) {
                return ResponseEntity.badRequest().build();
            }else{
                return ResponseEntity.ok().build();

            }

    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @PostMapping(value = "payCashcart")
    public ResponseEntity<?> payCashCart(@RequestBody List<PostPayDTO> entity, @RequestParam String email) {

        SaleEntity s = service.payCashCart(email, entity);
        if(s == null) {
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok().build();

        }

    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @GetMapping(value = "retiresale/{id}")
    public ResponseEntity<?> retire(@PathVariable int id) {
        SaleEntity sale = service.retireSale(id);
        if (sale == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
