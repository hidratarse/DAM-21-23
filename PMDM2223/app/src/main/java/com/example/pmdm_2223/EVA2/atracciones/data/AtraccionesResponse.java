package com.example.pmdm_2223.EVA2.atracciones.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AtraccionesResponse {
    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("ocupantes")
    @Expose
    private int ocupantes;

    @SerializedName("comentarios")
    @Expose
    private List<ComentariosResponse> comentarios;

    public String getUrl() {
        return url;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getOcupantes() {
        return ocupantes;
    }
    public List<ComentariosResponse> getComentarios() {
        return comentarios;
    }
}