package com.example.pmdm_2223.EVA2.atracciones.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComentariosResponse {
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("texto")
    @Expose
    private String texto;
    @SerializedName("atraccion")
    @Expose
    private String atraccion;

    public String getUrl() {
        return url;
    }

    public String getTexto() {
        return texto;
    }

    public String getAtraccion() {
        return atraccion;
    }
}
