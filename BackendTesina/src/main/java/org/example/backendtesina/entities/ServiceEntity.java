package org.example.backendtesina.entities;

import jakarta.persistence.*;
import org.example.backendtesina.entities.enums.ServiceStatus;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreCompleto;
    private String observacionesPrevias;
    private String auto;
    private int modelo;
    private ServiceStatus status;
    private LocalDateTime dateEntry;
    private LocalDateTime dateExit;

    private Double cost;
    @ManyToMany
    List<EmployeeEntity> empleados;
    @OneToOne
    private UserEntity client;
}
