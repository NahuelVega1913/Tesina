package org.example.backendtesina.services;

import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import jakarta.transaction.Transactional;
import org.example.backendtesina.DTOs.Get.DetailSaleDto;
import org.example.backendtesina.DTOs.Get.GetSaleDTO;
import org.example.backendtesina.DTOs.Post.PostPayDTO;
import org.example.backendtesina.entities.payment.DetailSaleEntity;
import org.example.backendtesina.entities.payment.SaleEntity;
import org.example.backendtesina.entities.payment.SpareEntity;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.entities.enums.typePaymentEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.repositories.CartRepository;
import org.example.backendtesina.repositories.SaleRepository;
import org.example.backendtesina.repositories.SpareRepository;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    SaleRepository repository;
    @Autowired
    UserRepository userRepository;




    @Autowired
    NotificationService notificationService;
    @Autowired
    JwtService jwtService;
    @Autowired
    SpareRepository spareRepository;
    @Autowired
    CartRepository cartRepository;




    public String consultarPago(String paymentId) {
        try {
            URL url = new URL("https://api.mercadopago.com/v1/payments/" + paymentId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer TU_ACCESS_TOKEN");
            connection.setRequestProperty("Content-Type", "application/json");

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString(); // JSON crudo con los datos del pago
            } else {
                System.out.println("Error al consultar pago. Código: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String confirmPayment(String s){

        return s;
    }

    @Transactional
    public String payMercadoPago(String token,PostPayDTO payDTO) throws MPException, MPApiException {
        //CONTROL
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        SpareEntity spare = spareRepository.findById(payDTO.getIdSpare()).get();
        if(payDTO.getQuantity() > spare.getStock()){
            return null;
        }
        spare.setStock(spare.getStock() - payDTO.getQuantity());
        spareRepository.save(spare);
        //REGISTRO DE ENTIDADES
        SaleEntity sale = new SaleEntity();
        sale.setUser(user);
        sale.setDate(LocalDate.now());
        List<DetailSaleEntity> lstDetails = new ArrayList<>();
        DetailSaleEntity detail = new DetailSaleEntity();
        detail.setPrice(new BigDecimal((spare.getPrice() -(spare.getPrice() * spare.getDiscaunt())/100)));
        detail.setCuantity(payDTO.getQuantity());
        detail.setSpare(spare);
        detail.setSale(sale);
        lstDetails.add(detail);
        sale.setDetails(lstDetails);
        sale.setTypePayment(typePaymentEntity.MERCADO_PAGO);
        repository.save(sale);
        notificationService.purchasedProduct(user);
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
                        .success("https://localhost:4200/repuestos")
                        .pending("https://localhost:4200/repuestos")
                        .failure("https://localhost:4200/repuestos")
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
    public String payProductos(String token,List<PostPayDTO> lstPay)throws MPException, MPApiException{
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElse(null);
     //REGISTRO DE ENTIDADES
        notificationService.purchasedProduct(user);
        

        SaleEntity sale = new SaleEntity();
        sale.setDate(LocalDate.now());
        sale.setUser(user);
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
       sale.setTypePayment(typePaymentEntity.MERCADO_PAGO);
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
                        .success("https://localhost:4200/repuestos")
                        .pending("https://localhost:4200/repuestos")
                        .failure("https://localhost:4200/repuestos")
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

    public List<GetSaleDTO> getAllSales(){
        List<SaleEntity> sales = repository.findAll();
        List<GetSaleDTO> reponse = new ArrayList<>();
        for (SaleEntity s:sales){
            GetSaleDTO dto = new GetSaleDTO();
            dto.setDate(s.getDate());
            dto.setUser(s.getUser().getName() +" "+s.getUser().getLastname());
            dto.setTypePayment(s.getTypePayment());
            dto.setId(s.getId());
            Double total = 0.00;
            for (DetailSaleEntity detail:s.getDetails()){
                total += new BigDecimal(detail.getCuantity()).multiply(detail.getPrice()).doubleValue();
            }
            dto.setTotal(total);
            reponse.add(dto);
        }
        return reponse;
    }
    public GetSaleDTO getDetails(int id){
        Optional<SaleEntity> sale = repository.findById(id);
        GetSaleDTO dto = new GetSaleDTO();
        dto.setDate(sale.get().getDate());
        dto.setUser(sale.get().getUser().getName() +" "+sale.get().getUser().getLastname());
        dto.setTypePayment(sale.get().getTypePayment());
        List<DetailSaleDto> details =new ArrayList<>();
        Double total = 0.00;
        for (DetailSaleEntity d:sale.get().getDetails()){
            DetailSaleDto dtoDetail = new DetailSaleDto();
            total += new BigDecimal(d.getCuantity()).multiply(d.getPrice()).doubleValue();
            dtoDetail.setName(d.getSpare().getName());
            dtoDetail.setBrand(d.getSpare().getBrand());
            dtoDetail.setPrice(d.getPrice());
            dtoDetail.setQuantity(d.getCuantity());
            details.add(dtoDetail);
        }
        dto.setTotal(total);
        dto.setDetails(details);
        return dto;
    }
}
