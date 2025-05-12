package org.example.backendtesina.DTOs.Post;

public class PostInspection {
    private int id;
    private int dni;
    private String nombreCompleto;
    private String observacionesPrevias;
    private String auto;
    private int modelo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
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
}
