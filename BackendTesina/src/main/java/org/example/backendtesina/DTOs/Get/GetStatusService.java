package org.example.backendtesina.DTOs.Get;

import org.example.backendtesina.entities.enums.ServiceStatus;

public class GetStatusService {
    private int id;
    private ServiceStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }
}
