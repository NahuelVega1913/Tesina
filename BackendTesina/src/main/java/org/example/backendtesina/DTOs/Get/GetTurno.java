package org.example.backendtesina.DTOs.Get;
import java.util.Date;

public class GetTurno {

    private Date fecha;
    private String horaInicio;
    private String horaFin;
    private int lugaresLibres;


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getLugaresLibres() {
        return lugaresLibres;
    }

    public void setLugaresLibres(int lugaresLibres) {
        this.lugaresLibres = lugaresLibres;
    }
}
