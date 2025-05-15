package org.example.backendtesina.DTOs.Get;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.backendtesina.DTOs.Login.RegisterDto;
import org.example.backendtesina.entities.enums.PaymentStatus;
import org.example.backendtesina.entities.enums.ServiceStatus;

import java.time.LocalDateTime;
import java.util.List;

public class GetServices {
    private String nombreCompleto;
    private String observacionesPrevias;
    private String auto;
    private int id;
    private int modelo;
    private String type;
    private ServiceStatus status;
    private PaymentStatus paymentStatus;
    List<GetEmployee> empleados;
    private LocalDateTime dateEntry;
    private LocalDateTime dateExit;
    private Double cost;
    private RegisterDto client;

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

    public List<GetEmployee> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<GetEmployee> empleados) {
        this.empleados = empleados;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public RegisterDto getClient() {
        return client;
    }

    public void setClient(RegisterDto client) {
        this.client = client;
    }
}
