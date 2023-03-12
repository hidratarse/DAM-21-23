package com.example.pmdm_2223.EVA2.bares.api;

import androidx.lifecycle.MutableLiveData;

import com.example.pmdm_2223.EVA2.bares.data.BaresResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaresRepository {
    private static final String API_URL = "http://192.168.1.39:8000/";
    private static BaresRepository instance;
    private BaresService service;
    private MutableLiveData<List<BaresResponse>> baresLiveData;

    public BaresRepository() {
        baresLiveData = new MutableLiveData<>();

        service = new retrofit2.Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BaresService.class);
    }

    public static BaresRepository getInstance() {
        if (instance == null) {
            instance = new BaresRepository();
        }
        return instance;
    }

    public void filtradoBares(int estrellas) {
        service.filtradoBares(estrellas)
                .enqueue(new Callback<List<BaresResponse>>() {
                    @Override
                    public void onResponse(Call<List<BaresResponse>> call, Response<List<BaresResponse>> response) {
                        if (response.body() != null) {
                            List<BaresResponse> listaBares = response.body();
                            baresLiveData.postValue(listaBares);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<BaresResponse>> call, Throwable t) {
                        List<BaresResponse> listaBares = new ArrayList<>();
                        baresLiveData.postValue(listaBares);
                    }
                });
    }

    public void getBares() {
        service.getBares()
                .enqueue(new Callback<List<BaresResponse>>() {
                    @Override
                    public void onResponse(Call<List<BaresResponse>> call, Response<List<BaresResponse>> response) {
                        if (response.body() != null) {
                            List<BaresResponse> listaBares = response.body();
                            baresLiveData.postValue(listaBares);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<BaresResponse>> call, Throwable t) {
                        List<BaresResponse> listaBares = new ArrayList<>();
                        baresLiveData.postValue(listaBares);
                    }
                });
    }

    public MutableLiveData<List<BaresResponse>> getBaresLiveData() {
        return baresLiveData;
    }
}