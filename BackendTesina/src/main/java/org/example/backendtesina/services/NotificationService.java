package org.example.backendtesina.services;

import org.example.backendtesina.entities.NotificationEntity;
import org.example.backendtesina.entities.UserEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.repositories.NotificationRepository;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    JwtService jwtService;
    @Autowired
    NotificationRepository repository;
    @Autowired
    UserRepository userRepository;

    public NotificationEntity createUserNotification(String token){
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        return null;
    }
    public NotificationEntity purchasedProduct(String token){
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        return null;
    }
    public NotificationEntity serviceFinalizedNotification(String token){
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        return null;
    }
    public NotificationEntity productSell(String token){
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        return null;
    }
    public NotificationEntity readNotifications(String token){
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        return null;
    }
}
