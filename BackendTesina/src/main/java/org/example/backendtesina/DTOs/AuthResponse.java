package org.example.backendtesina.DTOs;

import lombok.Builder;

import javax.management.relation.Role;


public class AuthResponse {
    String token;
    String name;
    String lastname;
    String role;

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(String token, String name, String lastname, String role) {
        this.token = token;
        this.name = name;
        this.lastname = lastname;
        this.role = role;
    }


    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
