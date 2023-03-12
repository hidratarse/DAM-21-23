package com.example.examen_pmdm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pelicula {

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("estrellas")
    @Expose
    private String estrellas;

    @SerializedName("actores")
    @Expose
    private List<Actor> actores;

    public String getUrl() {
        return url;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstrellas() {
        return estrellas;
    }

    public List<Actor> getActores() {
        return actores;
    }
}
