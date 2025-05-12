package org.example.backendtesina.entities.personal;

import jakarta.persistence.*;
import org.example.backendtesina.entities.enums.StateNotification;
import org.example.backendtesina.entities.enums.typeNotificationEntity;
import org.example.backendtesina.entities.personal.UserEntity;

import java.time.LocalDateTime;

@Entity
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    private String message;
    @Enumerated(EnumType.STRING)
    private typeNotificationEntity type;
    @Enumerated(EnumType.STRING)
    private StateNotification state;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public typeNotificationEntity getType() {
        return type;
    }

    public void setType(typeNotificationEntity type) {
        this.type = type;
    }

    public StateNotification getState() {
        return state;
    }

    public void setState(StateNotification state) {
        this.state = state;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
