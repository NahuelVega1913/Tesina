package org.example.backendtesina.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    //private static final String API_KEY = "252f6e2a-645b-4105-84b1-79882c1755c7";
    private static final String API_KEY1 = "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";
    private static final String API_KEY = "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";

    public String enviarMensaje(String mensaje) {
        RestTemplate restTemplate = new RestTemplate();

        // Headers requeridos
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);
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

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, request, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                // Parsear la respuesta
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> messageObj = (Map<String, Object>) choices.get(0).get("message");
                    String respuesta = (String) messageObj.get("content");
                    return respuesta;
                } else {
                    return "No se recibieron respuestas.";
                }
            } else {
                System.out.println("Respuesta no exitosa: " + response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
