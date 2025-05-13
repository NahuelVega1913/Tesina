package org.example.backendtesina.DTOs.Get;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.backendtesina.entities.enums.PaymentStatus;
import org.example.backendtesina.entities.enums.ServiceStatus;

import java.util.List;

public class GetServices {
    private String nombreCompleto;
    private String observacionesPrevias;
    private String auto;
    private int modelo;
    private ServiceStatus status;
    private PaymentStatus paymentStatus;
    List<GetEmployee> empleados;

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
}
