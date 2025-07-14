package org.example.backendtesina.entities.services;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TurnoEntity {

    @Id
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
