package org.example.backendtesina.entities;

import jakarta.persistence.*;
import org.example.backendtesina.entities.enums.typePaymentEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private typePaymentEntity typePayment;

    private LocalDate date;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailSaleEntity> details = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<DetailSaleEntity> getDetails() {
        return details;
    }

    public void setDetails(List<DetailSaleEntity> details) {
        this.details = details;
    }

    public typePaymentEntity getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(typePaymentEntity typePayment) {
        this.typePayment = typePayment;
    }
}
