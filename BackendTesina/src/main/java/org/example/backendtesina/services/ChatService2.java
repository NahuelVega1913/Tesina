package org.example.backendtesina.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ChatService2 {

    public String enviarMensaje(String mensaje) {
        RestTemplate restTemplate = new RestTemplate();

        // Headers requeridos
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " );
        headers.set("HTTP-Referer", "https://tusitio.com"); // Reemplazá por tu sitio real o localhost
        headers.set("X-Title", "My Java App"); // Título de tu aplicación

        // Armamos el cuerpo con el modelo gratuito
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", mensaje);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "mistralai/devstral-small:free");
        body.put("messages", List.of(message));

        // Enviamos el request
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        return  "";
    }
}
