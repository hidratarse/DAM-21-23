package com.example.pmdm_2223.EVA2.bares;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pmdm_2223.EVA2.bares.api.BaresRepository;
import com.example.pmdm_2223.EVA2.bares.data.BaresResponse;

import java.util.List;

public class BaresViewModel extends ViewModel {
    private MutableLiveData<List<BaresResponse>> data;
    private BaresRepository baresRepository;

    public void init(){
        baresRepository = BaresRepository.getInstance();
        data = baresRepository.getBaresLiveData();
    }

    public void filtradoBares(int estrellas){
        baresRepository.filtradoBares(estrellas);
    }

    public void getBares(){
        baresRepository.getBares();
    }

    public LiveData<List<BaresResponse>> getBaresResponse() {
        return data;
    }
}
