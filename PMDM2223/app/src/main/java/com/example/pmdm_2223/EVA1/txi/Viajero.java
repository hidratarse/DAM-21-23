package com.example.pmdm_2223.EVA1.txi;

import java.io.Serializable;
import java.util.Date;

public class Viajero implements Serializable {

    private String nombre;
    private String direccion;
    private String DNI;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String horaIda;
    private String horaVuelta;
    private Date fechaIda;
    private Date fechaVuelta;
    private  Boolean idavuelta=false;

    public Viajero(String nombre, String direccion, String DNI, String ciudadOrigen, String ciudadDestino, String horaIda, Date fechaIda) {
        super();
        this.nombre = nombre;
        this.direccion=direccion;
        this.DNI=DNI;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.horaIda=horaIda;
        this.fechaIda=fechaIda;
    }

    public Viajero(String nombre, String direccion, String DNI, String ciudadOrigen, String ciudadDestino, String horaIda, String horaVuelta, Date fechaIda, Date fechaVuelta) {
        super();
        this.nombre = nombre;
        this.direccion=direccion;
        this.DNI=DNI;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.horaIda=horaIda;
        this.horaVuelta=horaVuelta;
        this.fechaIda=fechaIda;
        this.fechaVuelta=fechaVuelta;
    }

    public Viajero() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getHoraIda() {
        return horaIda;
    }

    public void setHoraIda(String horaIda) {
        this.horaIda = horaIda;
    }

    public String getHoraVuelta() {
        return horaVuelta;
    }

    public void setHoraVuelta(String horaVuelta) {
        this.horaVuelta = horaVuelta;
    }

    public Date getFechaIda() {
        return fechaIda;
    }

    public void setFechaIda(Date fechaIda) {
        this.fechaIda = fechaIda;
    }

    public Date getFechaVuelta() {
        return fechaVuelta;
    }

    public void setFechaVuelta(Date fechaVuelta) {
        this.fechaVuelta = fechaVuelta;
    }

    public Boolean getIdavuelta() {return idavuelta;}

    public void setIdavuelta(Boolean idavuelta) {this.idavuelta = idavuelta;}
}
