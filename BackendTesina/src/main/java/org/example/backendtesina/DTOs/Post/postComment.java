package org.example.backendtesina.DTOs.Post;

import org.example.backendtesina.entities.enums.TypeComment;

import java.util.Date;

public class postComment {
    private int id;
    private String text;
    private String response;
    private TypeComment type;
    private Date fecha;

    private int idUser;
    private int idSpare;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdSpare() {
        return idSpare;
    }

    public void setIdSpare(int idSpare) {
        this.idSpare = idSpare;
    }

    public TypeComment getType() {
        return type;
    }

    public void setType(TypeComment type) {
        this.type = type;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
