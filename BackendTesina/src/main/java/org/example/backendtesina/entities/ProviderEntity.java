package org.example.backendtesina.entities;

import jakarta.persistence.*;
import org.example.backendtesina.entities.enums.CategorySpareEntity;
import org.example.backendtesina.entities.enums.CityEntity;
import org.example.backendtesina.entities.enums.CountryEntity;

import java.util.Date;

@Entity
@Table(name = "PROVIDERS")
public class ProviderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String adress;
    private CategorySpareEntity category;
    private CityEntity city;
    private CountryEntity county;

    private String CUIT;
    private String email;
    private int phone;
    private boolean state;

    private Date registerDate;
    private String remarks;



}
