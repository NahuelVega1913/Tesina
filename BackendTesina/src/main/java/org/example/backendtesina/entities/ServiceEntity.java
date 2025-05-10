package org.example.backendtesina.entities;

import jakarta.persistence.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
}
