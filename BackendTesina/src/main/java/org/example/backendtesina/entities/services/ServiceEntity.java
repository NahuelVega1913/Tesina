package org.example.backendtesina.entities.services;

import jakarta.persistence.*;
import org.example.backendtesina.entities.enums.PaymentStatus;
import org.example.backendtesina.entities.enums.TypeOfService;
import org.example.backendtesina.entities.personal.EmployeeEntity;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.entities.enums.ServiceStatus;

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
    private Double budget;
    private String auto;
    private int modelo;
    @Enumerated(EnumType.STRING)
    private ServiceStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private TypeOfService type;
    private LocalDateTime dateEntry;
    private LocalDateTime dateExit;

    private Double cost;
    @ManyToMany
    List<EmployeeEntity> empleados;
    @OneToOne
    private UserEntity client;

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getObservacionesPrevias() {
        return observacionesPrevias;
    }

    public void setObservacionesPrevias(String observacionesPrevias) {
        this.observacionesPrevias = observacionesPrevias;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(LocalDateTime dateEntry) {
        this.dateEntry = dateEntry;
    }

    public LocalDateTime getDateExit() {
        return dateExit;
    }

    public void setDateExit(LocalDateTime dateExit) {
        this.dateExit = dateExit;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<EmployeeEntity> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmployeeEntity> empleados) {
        this.empleados = empleados;
    }

    public UserEntity getClient() {
        return client;
    }

    public void setClient(UserEntity client) {
        this.client = client;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public TypeOfService getType() {
        return type;
    }

    public void setType(TypeOfService type) {
        this.type = type;
    }
}
