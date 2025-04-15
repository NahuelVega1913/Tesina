package org.example.backendtesina.DTOs;

import lombok.Data;

@Data
public class RegisterDto {

    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private String address;
    private String city;
}
