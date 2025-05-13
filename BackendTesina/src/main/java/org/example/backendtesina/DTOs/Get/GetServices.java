package org.example.backendtesina.DTOs.Get;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.backendtesina.entities.enums.ServiceStatus;

import java.util.List;

public class GetServices {
    private String nombreCompleto;
    private String observacionesPrevias;
    private String auto;
    private int modelo;
    private ServiceStatus status;
    List<GetEmployee> empleados;
}
