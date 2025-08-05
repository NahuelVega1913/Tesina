package org.example.backendtesina.DTOs.Post;

public class PostPayDTO {
    private String id;
    private double unitPrice;
    private int idSpare;

    private int quantity;

    public int getIdSpare() {
        return idSpare;
    }

    public void setIdSpare(int idSpare) {
        this.idSpare = idSpare;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
