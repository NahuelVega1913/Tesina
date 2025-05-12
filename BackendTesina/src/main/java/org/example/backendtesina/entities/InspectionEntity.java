package org.example.backendtesina.entities;

import jakarta.persistence.Entity;

@Entity
public class InspectionEntity extends ServiceEntity{

    private String resultado;
    private String recomendaciones;

    private String estadoGeneral;
}
