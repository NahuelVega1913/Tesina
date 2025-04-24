package org.example.backendtesina.entities;

import jakarta.persistence.*;
import org.example.backendtesina.entities.enums.CategorySpareEntity;

import java.util.List;

@Entity
@Table(name = "SPARES")
public class SpareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private int discaunt;
    private int stock;
    private String brand;
    @Enumerated(EnumType.STRING)
    private CategorySpareEntity category;
    private String description;

    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;



}
