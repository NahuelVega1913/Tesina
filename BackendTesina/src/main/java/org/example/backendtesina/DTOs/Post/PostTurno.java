package org.example.backendtesina.DTOs.Post;

import java.time.LocalDateTime;
import java.util.Date;

public class PostTurno {

    private Integer id;

    private LocalDateTime horaInicio;


    private  LocalDateTime horaFin;
    private String estado;

    private String EmailUser;

    private Integer ServiceId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }



    public Integer getServiceId() {
        return ServiceId;
    }

    public void setServiceId(Integer serviceId) {
        ServiceId = serviceId;
    }

    public String getEmailUser() {
        return EmailUser;
    }

    public void setEmailUser(String emailUser) {
        EmailUser = emailUser;
    }
}
