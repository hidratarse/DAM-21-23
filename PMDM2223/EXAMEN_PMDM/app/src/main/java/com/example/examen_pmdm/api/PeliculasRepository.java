package com.example.examen_pmdm.api;

import androidx.lifecycle.MutableLiveData;

import com.example.examen_pmdm.data.Pelicula;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeliculasRepository {
    private static final String API_URL = "http://51.77.156.235:3322/";
    private static PeliculasRepository instance;
    private PeliculasService service;
    private MutableLiveData<List<Pelicula>> peliculasLiveData;
    private MutableLiveData<Pelicula> detalleLiveData;

    public PeliculasRepository() {
        peliculasLiveData = new MutableLiveData<>();

        detalleLiveData = new MutableLiveData<>();

        service = new retrofit2.Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PeliculasService.class);
    }

    public static PeliculasRepository getInstance() {
        if (instance == null) {
            instance = new PeliculasRepository();
        }
        return instance;
    }

    public void getPeliculas() {
        service.getPeliculas()
                .enqueue(new Callback<List<Pelicula>>() {
                    @Override
                    public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                        List<Pelicula> listaPeliculas = response.body();
                        peliculasLiveData.postValue(listaPeliculas);
                    }

                    @Override
                    public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                        peliculasLiveData.postValue(new ArrayList<>());
                    }
                });
    }

    public void getDetalle(String url) {
        service.getDetalle(url)
                .enqueue(new Callback<Pelicula>() {
                    @Override
                    public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                        detalleLiveData.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Pelicula> call, Throwable t) {
                        detalleLiveData.postValue(null);
                    }
                });
    }

    public MutableLiveData<Pelicula> getDetalleLiveData() {
        return detalleLiveData;
    }

    public MutableLiveData<List<Pelicula>> getPeliculasLiveData() {
        return peliculasLiveData;
    }
}
