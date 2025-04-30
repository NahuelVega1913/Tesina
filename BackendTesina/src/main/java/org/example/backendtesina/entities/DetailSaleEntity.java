package org.example.backendtesina.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class DetailSaleEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int cuantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private SaleEntity sale;

    @ManyToOne
    @JoinColumn(name = "spare_id")
    private SpareEntity spare;
}
