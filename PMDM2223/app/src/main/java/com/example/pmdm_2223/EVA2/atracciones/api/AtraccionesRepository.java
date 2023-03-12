package com.example.pmdm_2223.EVA2.atracciones.api;

import androidx.lifecycle.MutableLiveData;

import com.example.pmdm_2223.EVA2.atracciones.data.AtraccionesResponse;
import com.example.pmdm_2223.EVA2.atracciones.data.ComentariosResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class AtraccionesRepository {
    private static final String API_URL = "http://192.168.1.39:8000/";
    private static AtraccionesRepository instance;
    private AtraccionesService service;
    private MutableLiveData<List<AtraccionesResponse>> atraccionesLiveData;
    private MutableLiveData<AtraccionesResponse> detalleLiveData;

    private String prueba;

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public String getPrueba() {
        return prueba;
    }

    public AtraccionesRepository() {
        atraccionesLiveData = new MutableLiveData<>();
        detalleLiveData = new MutableLiveData<>();

        service = new retrofit2.Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AtraccionesService.class);
    }

    public void getDetalle(String id){
        service.getDetalle(id)
                .enqueue(new Callback<AtraccionesResponse>() {
                    @Override
                    public void onResponse(Call<AtraccionesResponse> call, Response<AtraccionesResponse> response) {
                        if (response.body() != null) {
                            detalleLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<AtraccionesResponse> call, Throwable t) {
                        detalleLiveData.postValue(null);
                    }
                });
    }

    public void getAtracciones(){
        service.getAtracciones()
                .enqueue(new Callback<List<AtraccionesResponse>>() {
                    @Override
                    public void onResponse(Call<List<AtraccionesResponse>> call, Response<List<AtraccionesResponse>> response) {
                        if (response.body() != null) {
                            List<AtraccionesResponse> listaAtracciones = response.body();
                            atraccionesLiveData.postValue(listaAtracciones);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<AtraccionesResponse>> call, Throwable t) {
                        List<AtraccionesResponse> listaAtracciones = new ArrayList<>();
                        atraccionesLiveData.postValue(listaAtracciones);
                    }
                });
    }
    public static AtraccionesRepository getInstance() {
        if (instance == null) {
            instance = new AtraccionesRepository();
        }
        return instance;
    }
    public MutableLiveData<List<AtraccionesResponse>> getAtraccionesLiveData() {
        return atraccionesLiveData;
    }

    public MutableLiveData<AtraccionesResponse> getDetalleLiveData() {
        return detalleLiveData;
    }
}