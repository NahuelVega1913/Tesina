package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Get.GetNotification;
import org.example.backendtesina.entities.personal.NotificationEntity;
import org.example.backendtesina.entities.personal.UserEntity;
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
        List<NotificationEntity> notifications = user.getNotifications();
        List<GetNotification> dtoNotifications = new ArrayList<>();
        for(NotificationEntity entity:notifications) {
            GetNotification newNoti = new GetNotification();
            newNoti.setType(entity.getType().toString());
            newNoti.setTitle(entity.getTitle());
            newNoti.setMessage(entity.getMessage());
            newNoti.setDateTime(entity.getDateTime());
            newNoti.setId(entity.getId());
            newNoti.setState(entity.getState().toString());
            dtoNotifications.add(newNoti);
        }
        return dtoNotifications;
    }
    public NotificationEntity purchasedProduct(UserEntity user){

        NotificationEntity notification = new NotificationEntity();
        notification.setDateTime(LocalDateTime.now());
        notification.setTitle("Felicitaciones por tu compra!!!");
        notification.setMessage("Esperamos que disfrutes de tu producto!");
        notification.setType(typeNotificationEntity.SUCCESS);
        notification.setState(StateNotification.UNSEEN);
        notification.setUser(user);
        List<NotificationEntity> notifications = user.getNotifications();
        notifications.add(notification);
        user.setNotifications(notifications);
        repository.saveAll(notifications);
        return notification;
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
    public List<NotificationEntity> readNotifications(String token){
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        List<NotificationEntity> entities = user.getNotifications();
        for (NotificationEntity n:entities){
            n.setState(StateNotification.SEEN);
        }
        repository.saveAll(entities);
        return entities;
    }
}
