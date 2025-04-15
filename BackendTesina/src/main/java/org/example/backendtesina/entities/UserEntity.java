package org.example.backendtesina.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserEntity {

    @Id
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private String address;
    private String city;
}
