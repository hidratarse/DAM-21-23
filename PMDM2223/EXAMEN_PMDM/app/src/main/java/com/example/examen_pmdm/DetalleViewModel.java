package com.example.examen_pmdm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.examen_pmdm.api.PeliculasRepository;
import com.example.examen_pmdm.data.Pelicula;

public class DetalleViewModel extends ViewModel {
    private LiveData<Pelicula> detalleData;
    private PeliculasRepository peliculasRepository;

    public DetalleViewModel() {
        peliculasRepository = PeliculasRepository.getInstance();
        detalleData = peliculasRepository.getDetalleLiveData();
    }

    public void getDetalle(String url){
        peliculasRepository.getDetalle(url);
    }

    public LiveData<Pelicula> getDetalleData() {
        return detalleData;
    }
}
