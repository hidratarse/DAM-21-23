package com.example.pmdm_2223.EVA2.bares.api;

import com.example.pmdm_2223.EVA2.bares.data.BaresResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BaresService {
    @GET("pmdm/api/bares/")
    Call<List<BaresResponse>> filtradoBares(
            @Query("estrellas") int estrellas
    );
    @GET("pmdm/api/bares/")
    Call<List<BaresResponse>> getBares();
}