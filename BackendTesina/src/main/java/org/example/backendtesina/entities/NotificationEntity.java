package org.example.backendtesina.entities;

import jakarta.persistence.*;
import org.example.backendtesina.entities.enums.StateNotification;
import org.example.backendtesina.entities.enums.typeNotificationEntity;

import java.time.LocalDateTime;

@Entity
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String message;
    @Enumerated(EnumType.STRING)
    private typeNotificationEntity type;
    @Enumerated(EnumType.STRING)
    private StateNotification state;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
