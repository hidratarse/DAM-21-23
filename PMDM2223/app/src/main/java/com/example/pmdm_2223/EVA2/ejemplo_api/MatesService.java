package com.example.pmdm_2223.EVA2.ejemplo_api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MatesService {
    @GET("/")
    Call<Resultado> raiz();

    @GET("suma/{n1}/{n2}/")
    Call<Resultado> suma(@Path("n1") int n1, @Path("n2") int n2);

    @GET("resta/{n1}/{n2}/")
    Call<Resultado> resta(@Path("n1") int n1, @Path("n2") int n2);

    @GET("multiplicacion/{n1}/{n2}/")
    Call<Resultado> multiplicacion(@Path("n1") int n1, @Path("n2") int n2);

    @GET("division/{n1}/{n2}/")
    Call<Resultado> division(@Path("n1") int n1, @Path("n2") int n2);
}