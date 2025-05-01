package org.example.backendtesina.DTOs.Get;

import org.example.backendtesina.entities.enums.typePaymentEntity;

import java.time.LocalDate;
import java.util.List;

public class GetSaleDTO {
    private int id;
    private LocalDate date;
    private String user;
    private Double total;

    private typePaymentEntity typePayment;

    private List<DetailSaleDto> details;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public typePaymentEntity getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(typePaymentEntity typePayment) {
        this.typePayment = typePayment;
    }

    public List<DetailSaleDto> getDetails() {
        return details;
    }

    public void setDetails(List<DetailSaleDto> details) {
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
