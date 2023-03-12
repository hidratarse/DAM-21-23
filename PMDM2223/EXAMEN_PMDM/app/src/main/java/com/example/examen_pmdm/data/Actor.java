package com.example.examen_pmdm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Actor {
    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("pelicula")
    @Expose
    private String pelicula;

    public String getNombre() {
        return nombre;
    }

    public String getPelicula() {
        return pelicula;
    }

    public String getUrl() {
        return url;
    }
}
