package org.example.backendtesina.entities.services;

import jakarta.persistence.Entity;
import org.example.backendtesina.entities.services.ServiceEntity;

@Entity
public class InspectionEntity extends ServiceEntity {

    private String resultado;
    private String recomendaciones;

    private String estadoGeneral;

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getEstadoGeneral() {
        return estadoGeneral;
    }

    public void setEstadoGeneral(String estadoGeneral) {
        this.estadoGeneral = estadoGeneral;
    }
}
