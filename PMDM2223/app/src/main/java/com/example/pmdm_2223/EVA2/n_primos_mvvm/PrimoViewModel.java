package com.example.pmdm_2223.EVA2.n_primos_mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrimoViewModel extends ViewModel {

    private PrimoModel primoModel;

    private MutableLiveData<PrimoModel> dato;

    private ExecutorService executor;

    public PrimoViewModel(){
        primoModel = new PrimoModel();
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<PrimoModel> getDato(){
        if (dato==null){
            dato = new MutableLiveData<>();
            dato.setValue(primoModel);
        }
        return dato;
    }

    public void generaNPrimos(int rango1, int rango2){
        executor.execute(()->{
            primoModel.generarNPrimos(rango1, rango2);
            dato.postValue(primoModel);
        });
    }
}
