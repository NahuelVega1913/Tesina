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
import org.example.backendtesina.entities.enums.PaymentStatus;
import org.example.backendtesina.entities.payment.DetailSaleEntity;
import org.example.backendtesina.entities.payment.SaleEntity;
import org.example.backendtesina.entities.payment.SpareEntity;

import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.entities.enums.typePaymentEntity;
import org.example.backendtesina.entities.services.ServiceEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    SeviceRepository serviceRepository;

    @Autowired
    NotificationService notificationService;
    @Autowired
    JwtService jwtService;
    @Autowired
    SpareRepository spareRepository;
    @Autowired
    CartRepository cartRepository;




    public String consultarPago(String paymentId) {
        Logger logger = LoggerFactory.getLogger(SaleService.class);
        String accessToken = "APP_USR-6697469370584294-043012-15ec1f5d090244f4491e77dc501f75e0-2417547238";
        String url = "https://api.mercadopago.com/v1/payments/" + paymentId;

        try {
            logger.info("Consultando estado del pago con ID: {}", paymentId);

            // Configuración de la conexión HTTP
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + accessToken);

            // Leer la respuesta
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            } else {
            }
        } catch (IOException e) {
            logger.error("Error al realizar la consulta del pago.", e);
            return null;
        }
        return null;
    }




    public String confirmPayment(String webhookPayload) {
        Logger logger = LoggerFactory.getLogger(SaleService.class);
        String accessToken = "APP_USR-6697469370584294-043012-15ec1f5d090244f4491e77dc501f75e0-2417547238";

        try {
            logger.info("Payload recibido: {}", webhookPayload);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(webhookPayload);

            // Extraer el id de pago desde data.id
            if (!root.has("data") || !root.get("data").has("id")) {
                logger.error("No se encontró el campo data.id en el webhook.");
                return "Error: No se encontró el campo data.id en el webhook.";
            }
            String paymentId = root.get("data").get("id").asText();

            logger.info("Consultando estado del pago con ID: {}", paymentId);

            // Consultar a Mercado Pago por el pago real
            String url = "https://api.mercadopago.com/v1/payments/" + paymentId;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Convertir la respuesta en un objeto JSON
                JsonNode paymentData = objectMapper.readTree(response.toString());

                if (!paymentData.has("status") || !paymentData.has("external_reference")) {
                    return "Error: El campo 'status' o 'external_reference' no está presente en el payload.";
                }

                String paymentStatus = paymentData.get("status").asText();
                String externalReference = paymentData.get("external_reference").asText();

                if (!"approved".equals(paymentStatus)) {
                    logger.error("Estado del pago no válido: {}", paymentStatus);
                    return "Estado del pago no válido: " + paymentStatus;
                }

                // Lógica para manejar diferentes tipos de external_reference
                if (externalReference.startsWith("repuesto_")) {
                    int saleId = Integer.parseInt(externalReference.split("_")[1]);
                    SaleEntity sale = repository.findById(saleId)
                            .orElseThrow(() -> new RuntimeException("Venta no encontrada para el external_reference: " + externalReference));
                    sale.setStatus("approved");
                    repository.save(sale);
                    logger.info("Venta actualizada a estado 'approved'. ID de venta: {}", sale.getId());
                } else if (externalReference.startsWith("servicio_") || externalReference.startsWith("seña_")) {
                    int serviceId = Integer.parseInt(externalReference.split("_")[1]);
                    ServiceEntity service = serviceRepository.findById(serviceId)
                            .orElseThrow(() -> new RuntimeException("Servicio no encontrado para el external_reference: " + externalReference));
                    if (externalReference.startsWith("seña_")) {
                        service.setPaymentStatus(PaymentStatus.PAID_DEPOSIT);
                    } else {
                        service.setPaymentStatus(PaymentStatus.PAID);
                    }
                    serviceRepository.save(service);
                    logger.info("Servicio actualizado. ID de servicio: {}", service.getId());
                } else {
                    logger.error("Tipo de external_reference no reconocido: {}", externalReference);
                    return "Error: Tipo de external_reference no reconocido.";
                }

                return "Pago procesado correctamente.";
            } else {
                logger.error("Error al consultar el pago en Mercado Pago. Código de respuesta: {}", responseCode);
                return "Error al consultar el pago en Mercado Pago.";
            }
        } catch (Exception e) {
            logger.error("Error al procesar el pago: {}", e.getMessage(), e);
            return "Error al procesar el pago.";
        }
    }

    @Transactional
    public String payMercadoPago(String token, PostPayDTO payDTO) throws MPException, MPApiException {
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        SpareEntity spare = spareRepository.findById(payDTO.getIdSpare()).orElseThrow(() -> new RuntimeException("Repuesto no encontrado"));

        // Registrar la venta con estado "pending"
        SaleEntity sale = new SaleEntity();
        sale.setUser(user);
        sale.setRetired(Boolean.FALSE);
        sale.setDate(LocalDate.now());
        sale.setTypePayment(typePaymentEntity.MERCADO_PAGO);
        sale.setStatus("pending");

        DetailSaleEntity detail = new DetailSaleEntity();
        detail.setPrice(new BigDecimal((spare.getPrice() - (spare.getPrice() * spare.getDiscaunt()) / 100)));
        detail.setCuantity(payDTO.getQuantity());
        detail.setSpare(spare);
        detail.setSale(sale);

        sale.getDetails().add(detail);
        repository.save(sale);

        // Crear preferencia de pago
        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .id(String.valueOf(spare.getId()))
                .title(spare.getName())
                .description(spare.getDescription())
                .pictureUrl(spare.getImage1())
                .categoryId(spare.getCategory().toString())
                .quantity(payDTO.getQuantity())
                .unitPrice(detail.getPrice())
                .build();

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("https://servicios351.netlify.app")
                .pending("https://servicios351.netlify.app")
                .failure("https://servicios351.netlify.app")
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(List.of(itemRequest))
                .backUrls(backUrls)
                .autoReturn("approved")
                .externalReference("repuesto_" + sale.getId()) // Cambiar a "repuesto_ID"
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        return preference.getInitPoint();
    }
    @Transactional
    public String payProductos(String token, List<PostPayDTO> lstPay) throws MPException, MPApiException {
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Registrar la venta con estado "pending"
        SaleEntity sale = new SaleEntity();
        sale.setUser(user);
        sale.setRetired(Boolean.FALSE);
        sale.setDate(LocalDate.now());
        sale.setTypePayment(typePaymentEntity.MERCADO_PAGO);
        sale.setStatus("pending");

        List<DetailSaleEntity> details = new ArrayList<>();
        for (PostPayDTO dto : lstPay) {
            SpareEntity spare = spareRepository.findById(dto.getIdSpare()).orElseThrow(() -> new RuntimeException("Repuesto no encontrado"));

            DetailSaleEntity detail = new DetailSaleEntity();
            detail.setPrice(new BigDecimal((spare.getPrice() - (spare.getPrice() * spare.getDiscaunt()) / 100)));
            detail.setCuantity(dto.getQuantity());
            detail.setSpare(spare);
            detail.setSale(sale);

            details.add(detail);
        }

        sale.setDetails(details);
        repository.save(sale);

        // Crear preferencia de pago
        List<PreferenceItemRequest> items = new ArrayList<>();
        for (DetailSaleEntity detail : details) {
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(String.valueOf(detail.getSpare().getId()))
                    .title(detail.getSpare().getName())
                    .description(detail.getSpare().getDescription())
                    .pictureUrl(detail.getSpare().getImage1())
                    .categoryId(detail.getSpare().getCategory().toString())
                    .quantity(detail.getCuantity())
                    .unitPrice(detail.getPrice())
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
                .externalReference("repuesto_" + sale.getId()) // Cambiar a "repuesto_ID"
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        return preference.getInitPoint();
    }

    public List<GetSaleDTO> getAllSales() {
        List<SaleEntity> sales = repository.findAll();
        List<GetSaleDTO> response = new ArrayList<>();
        for (SaleEntity s : sales) {
            if (s.getStatus().equals("approved")) {
                GetSaleDTO dto = new GetSaleDTO();
                dto.setDate(s.getDate());
                dto.setRetired(s.getRetired());
                if (s.getUser() != null) {
                    dto.setUser(s.getUser().getName() + " " + s.getUser().getLastname());
                }
                dto.setTypePayment(s.getTypePayment());
                dto.setId(s.getId());
                Double total = 0.00;
                for (DetailSaleEntity detail : s.getDetails()) {
                    total += new BigDecimal(detail.getCuantity()).multiply(detail.getPrice()).doubleValue();
                }
                dto.setTotal(total);
                response.add(dto);
            }
        }
        // Ordenar por fecha de la más reciente a la más antigua
        response.sort((sale1, sale2) -> sale2.getDate().compareTo(sale1.getDate()));
        return response;
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
