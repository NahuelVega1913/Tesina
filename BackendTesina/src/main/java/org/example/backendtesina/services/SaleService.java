package org.example.backendtesina.services;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import kotlin.jvm.internal.SerializedIr;
import org.apache.velocity.runtime.directive.Parse;
import org.example.backendtesina.DTOs.Post.PostPayDTO;
import org.example.backendtesina.repositories.CartRepository;
import org.example.backendtesina.repositories.SaleRepository;
import org.example.backendtesina.repositories.SpareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Parser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    SaleRepository repository;

    @Autowired
    SpareRepository spareRepository;
    @Autowired
    CartRepository cartRepository;


    public String payMercadoPago(PostPayDTO payDTO){
        PreferenceItemRequest itemRequest =
                PreferenceItemRequest.builder()
                        .id("1234")
                        .title("Games")
                        .description("PS5")
                        .pictureUrl("http://picture.com/PS5")
                        .categoryId("games")
                        .quantity(2)
                        .currencyId("BRL")
                        .unitPrice(new BigDecimal("4000"))
                        .build();
        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items).build();
        PreferenceClient client = new PreferenceClient();
        //Preference preference = client.create(request);
        return itemRequest.getId();
    }
}
