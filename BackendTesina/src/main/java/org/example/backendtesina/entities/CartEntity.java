package org.example.backendtesina.entities;


import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.List;

@Entity
public class CartEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;


    private int idUser;
    private int idSpare;
    private int quantity;

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


}
