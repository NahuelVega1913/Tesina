package org.example.backendtesina.entities.payment;


import jakarta.persistence.*;
import org.example.backendtesina.entities.enums.TypeComment;

import java.util.Date;

@Entity
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "spare_id", nullable = false)
    private SpareEntity spare;

    private String text;

    private String response;
    private TypeComment type;

    private Date fecha;
    private String emailUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SpareEntity getSpare() {
        return spare;
    }

    public void setSpare(SpareEntity spare) {
        this.spare = spare;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public TypeComment getType() {
        return type;
    }

    public void setType(TypeComment type) {
        this.type = type;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }
}
