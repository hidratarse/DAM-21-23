package com.example.pmdm_2223.EVA2.atracciones.api;

import com.example.pmdm_2223.EVA2.atracciones.data.AtraccionesResponse;
import com.example.pmdm_2223.EVA2.atracciones.data.ComentariosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface AtraccionesService {
    @GET("pmdm/api/atracciones/")
    Call<List<AtraccionesResponse>> getAtracciones();

    @GET("pmdm/api/atracciones/{id}/")
    Call<AtraccionesResponse> getDetalle(@Path("id") String id);

    @GET
    Call<AtraccionesResponse> getDetalleV2(@Url String url);
}