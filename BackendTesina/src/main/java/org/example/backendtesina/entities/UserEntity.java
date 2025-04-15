package org.example.backendtesina.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserEntity {

    @Id
    private String email;
    private String password;
}
