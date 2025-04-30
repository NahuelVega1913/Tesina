package org.example.backendtesina.services;

import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import jakarta.transaction.Transactional;
import kotlin.jvm.internal.SerializedIr;
import org.apache.velocity.runtime.directive.Parse;
import org.example.backendtesina.DTOs.Post.PostPayDTO;
import org.example.backendtesina.entities.DetailSaleEntity;
import org.example.backendtesina.entities.SaleEntity;
import org.example.backendtesina.entities.SpareEntity;
import org.example.backendtesina.repositories.CartRepository;
import org.example.backendtesina.repositories.SaleRepository;
import org.example.backendtesina.repositories.SpareRepository;
import org.intellij.lang.annotations.JdkConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Parser;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Transactional
    public String payMercadoPago(PostPayDTO payDTO) throws MPException, MPApiException {
        //CONTROL
        SpareEntity spare = spareRepository.findById(payDTO.getIdSpare()).get();
        if(payDTO.getQuantity() > spare.getStock()){
            return null;
        }
        spare.setStock(spare.getStock() - payDTO.getQuantity());
        spareRepository.save(spare);
        //REGISTRO DE ENTIDADES
        SaleEntity sale = new SaleEntity();
        sale.setDate(LocalDate.now());
        List<DetailSaleEntity> lstDetails = new ArrayList<>();
        DetailSaleEntity detail = new DetailSaleEntity();
        detail.setPrice(new BigDecimal((spare.getPrice() -(spare.getPrice() * spare.getDiscaunt())/100)));
        detail.setCuantity(payDTO.getQuantity());
        detail.setSpare(spare);
        detail.setSale(sale);
        lstDetails.add(detail);
        sale.setDetails(lstDetails);
        repository.save(sale);
        //MERCADO PAGO
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
    @Transactional
    public String payProductos(List<PostPayDTO> lstPay)throws MPException, MPApiException{

     //REGISTRO DE ENTIDADES
        SaleEntity sale = new SaleEntity();
        sale.setDate(LocalDate.now());
        List<DetailSaleEntity> lstDetails = new ArrayList<>();
       for (PostPayDTO dto:lstPay){
           SpareEntity spare = spareRepository.findById(dto.getIdSpare()).get();
           if(dto.getQuantity() > spare.getStock()){
               return null;
           }
           else{
               spare.setStock(spare.getStock()- dto.getQuantity());
           }
           spareRepository.save(spare);

           DetailSaleEntity detail = new DetailSaleEntity();
           detail.setSpare(spare);
           detail.setCuantity(dto.getQuantity());
           detail.setSale(sale);
           detail.setPrice(new BigDecimal((spare.getPrice() -(spare.getPrice() * spare.getDiscaunt())/100)));
           lstDetails.add(detail);
       }
       sale.setDetails(lstDetails);
       repository.save(sale);
       //MERCADO PAGO
        List<PreferenceItemRequest> items = new ArrayList<>();
        for (PostPayDTO dto:lstPay){
            SpareEntity spare = spareRepository.findById(dto.getIdSpare()).get();
            PreferenceItemRequest itemRequest =
                    PreferenceItemRequest.builder()
                            .id(String.valueOf(dto.getIdSpare()))
                            .title(spare.getName())
                            .description(spare.getDescription())
                            .pictureUrl(spare.getImage1())
                            .categoryId(spare.getCategory().toString())
                            .quantity(dto.getQuantity())
                            .unitPrice(new BigDecimal((spare.getPrice() -(spare.getPrice() * spare.getDiscaunt())/100)))
                            .build();
            items.add(itemRequest);
        }
        PreferenceBackUrlsRequest backUrls =
                PreferenceBackUrlsRequest.builder()
                        .success("https://www.promiedos.com.ar/league/liga-profesional/hc")
                        .pending("https://nahuel-vega.atlassian.net/jira/software/projects/SCRUM/boards/1/backlog")
                        .failure("https://www.vexorpay.com/dashboard/apikeys")
                        .build();
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
