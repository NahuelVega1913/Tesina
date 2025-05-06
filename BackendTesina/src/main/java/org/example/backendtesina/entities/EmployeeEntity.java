package org.example.backendtesina.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.Date;

@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date birthDate;

    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String position;
    private String dateOfEntry;



}
