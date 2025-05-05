package org.example.backendtesina.DTOs.Get;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.backendtesina.entities.UserEntity;
import org.example.backendtesina.entities.enums.StateNotification;
import org.example.backendtesina.entities.enums.typeNotificationEntity;

import java.time.LocalDateTime;

public class GetNotification {

    private int id;
    private String title;

    private String message;

    private String type;

    private String state;

    private LocalDateTime dateTime;

}
