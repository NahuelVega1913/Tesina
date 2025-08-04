package org.example.backendtesina.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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


    public String confirmPayment(String webhookPayload) {
        try {
            // Parsear el payload del webhook
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(webhookPayload);

            // Extraer el ID del pago
            String paymentId = jsonNode.get("data").get("id").asText();

            // Consultar el estado del pago en la API de Mercado Pago
            String paymentResponse = consultarPago(paymentId);
            if (paymentResponse == null) {
                return "Error al consultar el estado del pago.";
            }

            // Parsear la respuesta de la API de Mercado Pago
            JsonNode paymentData = objectMapper.readTree(paymentResponse);
            String paymentStatus = paymentData.get("status").asText();

            // Verificar si el pago fue aprobado
            if ("approved".equals(paymentStatus)) {
                // Procesar la venta
                JsonNode metadata = paymentData.get("metadata");
                String email = metadata.get("email").asText();
                List<PostPayDTO> items = objectMapper.convertValue(
                        metadata.get("items"),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, PostPayDTO.class)
                );

                UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                SaleEntity sale = new SaleEntity();
                sale.setUser(user);
                sale.setRetired(Boolean.FALSE);
                sale.setDate(LocalDate.now());
                sale.setTypePayment(typePaymentEntity.MERCADO_PAGO);

                List<DetailSaleEntity> details = new ArrayList<>();
                for (PostPayDTO dto : items) {
                    SpareEntity spare = spareRepository.findById(dto.getIdSpare()).orElseThrow(() -> new RuntimeException("Repuesto no encontrado"));
                    if (dto.getQuantity() > spare.getStock()) {
                        throw new RuntimeException("Stock insuficiente para el producto: " + spare.getName());
                    }

                    spare.setStock(spare.getStock() - dto.getQuantity());
                    spareRepository.save(spare);

                    DetailSaleEntity detail = new DetailSaleEntity();
                    detail.setPrice(new BigDecimal((spare.getPrice() - (spare.getPrice() * spare.getDiscaunt()) / 100)));
                    detail.setCuantity(dto.getQuantity());
                    detail.setSpare(spare);
                    detail.setSale(sale);

                    details.add(detail);
                }

                sale.setDetails(details);
                repository.save(sale);

                notificationService.purchasedProduct(user);
                return "Pago procesado correctamente.";
            } else {
                return "El pago no fue aprobado. Estado: " + paymentStatus;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al procesar el webhook.";
        }
    }

    @Transactional
    public String payMercadoPago(String token, PostPayDTO payDTO) throws MPException, MPApiException {
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        SpareEntity spare = spareRepository.findById(payDTO.getIdSpare()).orElseThrow(() -> new RuntimeException("Repuesto no encontrado"));

        // Crear preferencia de pago
        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .id(String.valueOf(spare.getId()))
                .title(spare.getName())
                .description(spare.getDescription())
                .pictureUrl(spare.getImage1())
                .categoryId(spare.getCategory().toString())
                .quantity(payDTO.getQuantity())
                .unitPrice(new BigDecimal((spare.getPrice() - (spare.getPrice() * spare.getDiscaunt()) / 100)))
                .build();

        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("https://servicios351.netlify.app")
                .pending("https://servicios351.netlify.app")
                .failure("https://servicios351.netlify.app")
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
    @Transactional
    public String payProductos(String token, List<PostPayDTO> lstPay) throws MPException, MPApiException {
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<PreferenceItemRequest> items = new ArrayList<>();
        for (PostPayDTO dto : lstPay) {
            SpareEntity spare = spareRepository.findById(dto.getIdSpare()).orElseThrow(() -> new RuntimeException("Repuesto no encontrado"));

            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(String.valueOf(spare.getId()))
                    .title(spare.getName())
                    .description(spare.getDescription())
                    .pictureUrl(spare.getImage1())
                    .categoryId(spare.getCategory().toString())
                    .quantity(dto.getQuantity())
                    .unitPrice(new BigDecimal((spare.getPrice() - (spare.getPrice() * spare.getDiscaunt()) / 100)))
                    .build();

            items.add(itemRequest);
        }

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("https://servicios351.netlify.app")
                .pending("https://servicios351.netlify.app")
                .failure("https://servicios351.netlify.app")
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
            dto.setRetired(s.getRetired());
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


    public SaleEntity payCash(String email, PostPayDTO payDTO) {
        // Obtener el usuario desde el correo electrónico
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener el repuesto y verificar stock
        SpareEntity spare = spareRepository.findById(payDTO.getIdSpare()).orElseThrow(() -> new RuntimeException("Repuesto no encontrado"));
        if (payDTO.getQuantity() > spare.getStock()) {
            throw new RuntimeException("Stock insuficiente");
        }

        // Actualizar el stock del repuesto
        spare.setStock(spare.getStock() - payDTO.getQuantity());
        spareRepository.save(spare);

        // Registrar la venta
        SaleEntity sale = new SaleEntity();
        sale.setUser(user);
        sale.setRetired(Boolean.TRUE);
        sale.setDate(LocalDate.now());
        sale.setTypePayment(typePaymentEntity.CASH);

        // Registrar el detalle de la venta
        DetailSaleEntity detail = new DetailSaleEntity();
        detail.setPrice(new BigDecimal((spare.getPrice() - (spare.getPrice() * spare.getDiscaunt()) / 100)));
        detail.setCuantity(payDTO.getQuantity());
        detail.setSpare(spare);
        detail.setSale(sale);

        // Guardar la venta y el detalle
        sale.getDetails().add(detail);
        repository.save(sale);

        // Notificar al usuario
        notificationService.purchasedProduct(user);
        return sale;
    }
    public SaleEntity payCashCart(String email, List<PostPayDTO> payDTOList) {
        // Obtener el usuario desde el correo electrónico
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear la venta
        SaleEntity sale = new SaleEntity();
        sale.setUser(user);
        sale.setRetired(Boolean.TRUE);
        sale.setDate(LocalDate.now());
        sale.setTypePayment(typePaymentEntity.CASH);

        List<DetailSaleEntity> details = new ArrayList<>();

        // Procesar cada producto en el carrito
        for (PostPayDTO payDTO : payDTOList) {
            // Obtener el repuesto y verificar stock
            SpareEntity spare = spareRepository.findById(payDTO.getIdSpare()).orElseThrow(() -> new RuntimeException("Repuesto no encontrado"));
            if (payDTO.getQuantity() > spare.getStock()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + spare.getName());
            }

            // Actualizar el stock del repuesto
            spare.setStock(spare.getStock() - payDTO.getQuantity());
            spareRepository.save(spare);

            // Crear el detalle de la venta
            DetailSaleEntity detail = new DetailSaleEntity();
            detail.setPrice(new BigDecimal((spare.getPrice() - (spare.getPrice() * spare.getDiscaunt()) / 100)));
            detail.setCuantity(payDTO.getQuantity());
            detail.setSpare(spare);
            detail.setSale(sale);

            details.add(detail);
        }

        // Asociar los detalles a la venta
        sale.setDetails(details);

        // Guardar la venta
        repository.save(sale);

        // Notificar al usuario
        notificationService.purchasedProduct(user);

        return sale;
    }
    public SaleEntity retireSale (int id){
       SaleEntity entity =  this.repository.findById(id).get();
       entity.setRetired(Boolean.TRUE);
       repository.save(entity);
       return entity;
    }
}
