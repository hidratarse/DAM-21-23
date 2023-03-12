package com.example.examen_pmdm.api;

import com.example.examen_pmdm.data.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface PeliculasService {
    @GET("pmdm/api/peliculas/")
    Call<List<Pelicula>> getPeliculas();
    @GET
    Call<Pelicula> getDetalle(@Url String url);
}
