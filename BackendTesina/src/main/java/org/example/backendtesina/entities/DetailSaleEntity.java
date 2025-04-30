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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCuantity() {
        return cuantity;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public SaleEntity getSale() {
        return sale;
    }

    public void setSale(SaleEntity sale) {
        this.sale = sale;
    }

    public SpareEntity getSpare() {
        return spare;
    }

    public void setSpare(SpareEntity spare) {
        this.spare = spare;
    }
}
