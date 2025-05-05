package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Get.GetNotification;
import org.example.backendtesina.entities.NotificationEntity;
import org.example.backendtesina.entities.UserEntity;
import org.example.backendtesina.entities.enums.StateNotification;
import org.example.backendtesina.entities.enums.typeNotificationEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.repositories.NotificationRepository;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    JwtService jwtService;
    @Autowired
    NotificationRepository repository;
    @Autowired
    UserRepository userRepository;

    public NotificationEntity createUserNotification(UserEntity user){
        NotificationEntity notification = new NotificationEntity();
        notification.setDateTime(LocalDateTime.now());
        notification.setTitle("Bienvenido!!!");
        notification.setMessage("El equipo de MechanicTech te desea una gran bienvenida!");
        notification.setType(typeNotificationEntity.SUCCESS);
        notification.setState(StateNotification.UNSEEN);
        notification.setUser(user);

            List<NotificationEntity> notifications = new ArrayList<>();
            notifications.add(notification);
            user.setNotifications(notifications);

        repository.save(notification);
        return notification;
    }
    public List<GetNotification> getAll(String token){
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
