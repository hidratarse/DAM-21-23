package com.example.pmdm_2223.EVA2.autentificacion.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenResponse {
    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }
}
