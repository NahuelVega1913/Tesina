package org.example.backendtesina.entities.payment;


import jakarta.persistence.*;
import org.example.backendtesina.entities.personal.UserEntity;

import java.util.List;

@Entity
public class CartEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;


    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany
    @JoinTable(
            name = "cart_spare",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "spare_id")
    )
    private List<SpareEntity> spares;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<SpareEntity> getSpares() {
        return spares;
    }

    public void setSpares(List<SpareEntity> spares) {
        this.spares = spares;
    }
}
