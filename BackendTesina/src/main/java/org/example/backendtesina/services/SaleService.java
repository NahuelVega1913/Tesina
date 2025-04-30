package org.example.backendtesina.services;

import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import kotlin.jvm.internal.SerializedIr;
import org.apache.velocity.runtime.directive.Parse;
import org.example.backendtesina.DTOs.Post.PostPayDTO;
import org.example.backendtesina.entities.SpareEntity;
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


    public String payMercadoPago(PostPayDTO payDTO) throws MPException, MPApiException {
        SpareEntity spare = spareRepository.findById(payDTO.getIdSpare()).get();

        PreferenceItemRequest itemRequest =
                PreferenceItemRequest.builder()
                        .id(String.valueOf(spare.getId()))
                        .title(spare.getName())
                        .description(spare.getDescription())
                        .pictureUrl(spare.getImage1())
                        .categoryId(spare.getCategory().toString())
                        .quantity(payDTO.getQuantity())
                        .unitPrice(new BigDecimal((spare.getPrice() -(spare.getPrice() * spare.getDiscaunt())/100)))
                        .build();
        List<PreferenceItemRequest> items = new ArrayList<>();
        PreferenceBackUrlsRequest backUrls =
                PreferenceBackUrlsRequest.builder()
                        .success("https://www.promiedos.com.ar/league/liga-profesional/hc")
                        .pending("https://nahuel-vega.atlassian.net/jira/software/projects/SCRUM/boards/1/backlog")
                        .failure("https://www.vexorpay.com/dashboard/apikeys")
                        .build();

        //PreferenceRequest request = PreferenceRequest.builder().backUrls(backUrls).build();
        items.add(itemRequest);

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .backUrls(backUrls)
                .autoReturn("approved")
                .build();
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);
        return preference.getInitPoint();
    }
}
