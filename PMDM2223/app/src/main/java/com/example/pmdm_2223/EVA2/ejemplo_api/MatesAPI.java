package com.example.pmdm_2223.EVA2.ejemplo_api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MatesAPI {
    private  static MatesAPI instance;

    MatesService service;

    private  MatesAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.20.115:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MatesService.class);
    }

    public static MatesAPI getInstance(){
        if(instance==null){
            instance= new MatesAPI();
        }
            return instance;
    }

    public Call<Resultado> suma(int n1, int n2){
        return service.suma(n1, n2);
    }

    public Call<Resultado> resta(int n1, int n2){
        return service.resta(n1, n2);
    }

    public Call<Resultado> multiplicacion(int n1, int n2){return service.multiplicacion(n1, n2);}

    public Call<Resultado> division(int n1, int n2){return service.division(n1, n2);}
}