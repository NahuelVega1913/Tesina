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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
