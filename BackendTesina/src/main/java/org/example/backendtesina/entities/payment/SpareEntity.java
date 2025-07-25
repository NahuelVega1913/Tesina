package org.example.backendtesina.entities.payment;

import jakarta.persistence.*;
import org.example.backendtesina.entities.enums.CategorySpareEntity;
import org.example.backendtesina.entities.payment.CartEntity;
import org.example.backendtesina.entities.personal.ProviderEntity;

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
    private boolean active;
    private int stock;
    private String brand;
    @ManyToOne
    @JoinColumn(name = "provider_id") // nombre de la FK en la tabla repuesto
    private ProviderEntity provider;
    @Enumerated(EnumType.STRING)
    private CategorySpareEntity category;
    private String description;
    private Double stars;

    @ManyToMany(mappedBy = "spares")
    private List<CartEntity> carts;

    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;

    @OneToMany(mappedBy = "spare")
    private List<CommentEntity> comments;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getDiscaunt() {
        return discaunt;
    }
    public void setDiscaunt(int discaunt) {
        this.discaunt = discaunt;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public CategorySpareEntity getCategory() {
        return category;
    }
    public void setCategory(CategorySpareEntity category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage1() {
        return image1;
    }
    public void setImage1(String image1) {
        this.image1 = image1;
    }
    public String getImage2() {
        return image2;
    }
    public void setImage2(String image2) {
        this.image2 = image2;
    }
    public String getImage3() {
        return image3;
    }
    public void setImage3(String image3) {
        this.image3 = image3;
    }
    public String getImage4() {
        return image4;
    }
    public void setImage4(String image4) {
        this.image4 = image4;
    }
    public String getImage5() {
        return image5;
    }
    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public ProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(ProviderEntity proveedor) {
        this.provider = proveedor;
    }

    public List<CartEntity> getCarts() {
        return carts;
    }

    public void setCarts(List<CartEntity> carts) {
        this.carts = carts;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }
}
